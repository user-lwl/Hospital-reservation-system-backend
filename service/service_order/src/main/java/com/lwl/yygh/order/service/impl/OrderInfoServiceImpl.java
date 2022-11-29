package com.lwl.yygh.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.common.rabbit.constant.MqConst;
import com.lwl.common.rabbit.service.RabbitService;
import com.lwl.yygh.common.exception.YyghException;
import com.lwl.yygh.common.helper.HttpRequestHelper;
import com.lwl.yygh.common.result.ResultCodeEnum;
import com.lwl.yygh.enums.OrderStatusEnum;
import com.lwl.yygh.hosp.client.HospitalFeignClient;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.model.user.Patient;
import com.lwl.yygh.order.mapper.OrderInfoMapper;
import com.lwl.yygh.order.service.OrderInfoService;
import com.lwl.yygh.user.client.PatientFeignClient;
import com.lwl.yygh.vo.hosp.ScheduleOrderVo;
import com.lwl.yygh.vo.msm.MsmVo;
import com.lwl.yygh.vo.order.OrderMqVo;
import com.lwl.yygh.vo.order.SignInfoVo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
* @author user-lwl
* @description 针对表【order_info(订单表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:15
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private PatientFeignClient patientFeignClient;

    @Resource
    private HospitalFeignClient hospitalFeignClient;

    @Resource
    private RabbitService rabbitService;

    /**
     * 生成挂号订单
     * @param scheduleId 排班id
     * @param patientId 就诊人id
     * @return 订单id
     */
    @Override
    public Long saveOrder(String scheduleId, Long patientId) {
        //获取就诊人信息
        Patient patient = patientFeignClient.getPatientOrder(patientId);
        //获取排班信息
        ScheduleOrderVo scheduleOrderVo = hospitalFeignClient.getScheduleOrderVo(scheduleId);
        //判断时间是否可预约
        if(new DateTime(scheduleOrderVo.getStartTime()).isAfterNow()
                || new DateTime(scheduleOrderVo.getEndTime()).isBeforeNow()) {
            throw new YyghException(ResultCodeEnum.TIME_NO);
        }
        //获取签名信息
        SignInfoVo signInfoVo = hospitalFeignClient.getSignInfoVo(scheduleOrderVo.getHoscode());
        //添加订单表
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(scheduleOrderVo, orderInfo);
        Random random;
        String outTradeNo = null;
        try {
            random = SecureRandom.getInstanceStrong();
            outTradeNo = System.currentTimeMillis() + ""+ random.nextInt(100);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        orderInfo.setOutTradeNo(outTradeNo);
        orderInfo.setHosScheduleId(scheduleId);
        orderInfo.setUserId(patient.getUserId());
        orderInfo.setPatientId(patientId);
        orderInfo.setPatientName(patient.getName());
        orderInfo.setPatientPhone(patient.getPhone());
        orderInfo.setOrderStatus(OrderStatusEnum.UNPAID.getStatus());
        this.save(orderInfo);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hoscode",orderInfo.getHoscode());
        paramMap.put("depcode",orderInfo.getDepcode());
        paramMap.put("hosScheduleId",orderInfo.getHosScheduleId());
        paramMap.put("reserveDate",new DateTime(orderInfo.getReserveDate()).toString("yyyy-MM-dd"));
        paramMap.put("reserveTime", orderInfo.getReserveTime());
        paramMap.put("amount",orderInfo.getAmount());
        paramMap.put("name", patient.getName());
        paramMap.put("certificatesType",patient.getCertificatesType());
        paramMap.put("certificatesNo", patient.getCertificatesNo());
        paramMap.put("sex",patient.getSex());
        paramMap.put("birthdate", patient.getBirthdate());
        paramMap.put("phone",patient.getPhone());
        paramMap.put("isMarry", patient.getIsMarry());
        paramMap.put("provinceCode",patient.getProvinceCode());
        paramMap.put("cityCode", patient.getCityCode());
        paramMap.put("districtCode",patient.getDistrictCode());
        paramMap.put("address",patient.getAddress());
        //联系人
        paramMap.put("contactsName",patient.getContactsName());
        paramMap.put("contactsCertificatesType", patient.getContactsCertificatesType());
        paramMap.put("contactsCertificatesNo",patient.getContactsCertificatesNo());
        paramMap.put("contactsPhone",patient.getContactsPhone());
        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        String sign = HttpRequestHelper.getSign(paramMap, signInfoVo.getSignKey());
        paramMap.put("sign", sign);
        JSONObject result = HttpRequestHelper.sendRequest(paramMap, signInfoVo.getApiUrl()+"/order/submitOrder");

        if(result.getInteger("code") == 200) {
            JSONObject jsonObject = result.getJSONObject("data");
            //预约记录唯一标识（医院预约记录主键）
            String hosRecordId = jsonObject.getString("hosRecordId");
            //预约序号
            Integer number = jsonObject.getInteger("number");
            //取号时间
            String fetchTime = jsonObject.getString("fetchTime");
            //取号地址
            String fetchAddress = jsonObject.getString("fetchAddress");
            //更新订单
            orderInfo.setHosRecordId(hosRecordId);
            orderInfo.setNumber(number);
            orderInfo.setFetchTime(fetchTime);
            orderInfo.setFetchAddress(fetchAddress);
            orderInfoMapper.updateById(orderInfo);
            //排班可预约数
            Integer reservedNumber = jsonObject.getInteger("reservedNumber");
            //排班剩余预约数
            Integer availableNumber = jsonObject.getInteger("availableNumber");
            //发送mq信息更新号源和短信通知
            //发送mq号源更新
            OrderMqVo orderMqVo = new OrderMqVo();
            orderMqVo.setScheduleId(scheduleId);
            orderMqVo.setReservedNumber(reservedNumber);
            orderMqVo.setAvailableNumber(availableNumber);
            //短信提示
            MsmVo msmVo = new MsmVo();
            msmVo.setPhone(orderInfo.getPatientPhone());
            String reserveDate =
                    new DateTime(orderInfo.getReserveDate()).toString("yyyy-MM-dd")
                            + (orderInfo.getReserveTime()==0 ? "上午": "下午");
            Map<String,Object> param = new HashMap<>();
            param.put("title", orderInfo.getHosname()+"|"+orderInfo.getDepname()+"|"+orderInfo.getTitle());
            param.put("amount", orderInfo.getAmount());
            param.put("reserveDate", reserveDate);
            param.put("name", orderInfo.getPatientName());
            param.put("quitTime", new DateTime(orderInfo.getQuitTime()).toString("yyyy-MM-dd HH:mm"));
            msmVo.setParam(param);
            orderMqVo.setMsmVo(msmVo);
            //发送
            rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_ORDER, MqConst.ROUTING_ORDER, orderMqVo);
        } else {
            throw new YyghException(result.getString("message"), ResultCodeEnum.FAIL.getCode());
        }
        return orderInfo.getId();
    }
}




