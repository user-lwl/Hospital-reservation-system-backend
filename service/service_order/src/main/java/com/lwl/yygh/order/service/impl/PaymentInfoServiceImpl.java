package com.lwl.yygh.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.common.exception.YyghException;
import com.lwl.yygh.common.helper.HttpRequestHelper;
import com.lwl.yygh.common.result.ResultCodeEnum;
import com.lwl.yygh.enums.OrderStatusEnum;
import com.lwl.yygh.enums.PaymentStatusEnum;
import com.lwl.yygh.enums.PaymentTypeEnum;
import com.lwl.yygh.hosp.client.HospitalFeignClient;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.model.order.PaymentInfo;
import com.lwl.yygh.order.mapper.PaymentInfoMapper;
import com.lwl.yygh.order.service.OrderInfoService;
import com.lwl.yygh.order.service.PaymentInfoService;
import com.lwl.yygh.vo.order.SignInfoVo;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author user-lwl
* @description 针对表【payment_info(支付信息表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:19
*/
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
    implements PaymentInfoService {
    @Resource
    private PaymentInfoMapper paymentInfoMapper;

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private HospitalFeignClient hospitalFeignClient;

    public static final String PAYMENT_TYPE = "payment_type";

    /**
     * 保存交易记录
     * @param orderInfo OrderInfo
     * @param paymentType 支付类型（1：微信 2：支付宝）
     */
    @Override
    public void savePaymentInfo(OrderInfo orderInfo, Integer paymentType) {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderInfo.getId());
        queryWrapper.eq(PAYMENT_TYPE, paymentType);
        Integer count = paymentInfoMapper.selectCount(queryWrapper);
        if(count >0) {
            return;
        }
        // 保存交易记录
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setOrderId(orderInfo.getId());
        paymentInfo.setPaymentType(paymentType);
        paymentInfo.setOutTradeNo(orderInfo.getOutTradeNo());
        paymentInfo.setPaymentStatus(PaymentStatusEnum.UNPAID.getStatus());
        String subject = new DateTime(orderInfo.getReserveDate()).toString("yyyy-MM-dd")+"|"+orderInfo.getHosname()+"|"+orderInfo.getDepname()+"|"+orderInfo.getTitle();
        paymentInfo.setSubject(subject);
        paymentInfo.setTotalAmount(new BigDecimal(orderInfo.getAmount()));
        paymentInfoMapper.insert(paymentInfo);

    }

    /**
     * 更新订单状态
     * @param outTradeNo 订单编号
     * @param resultMap 订单信息
     */
    @Override
    public void paySuccess(String outTradeNo, Map<String, String> resultMap) {
        //1 根据订单编号得到支付记录
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no", outTradeNo);
        queryWrapper.eq(PAYMENT_TYPE, PaymentTypeEnum.WEIXIN.getStatus());
        PaymentInfo paymentInfo = paymentInfoMapper.selectOne(queryWrapper);
        //2 更新支付记录信息
        paymentInfo.setPaymentStatus(PaymentStatusEnum.PAID.getStatus());
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setTradeNo(resultMap.get("transaction_id"));
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(resultMap.toString());
        paymentInfoMapper.updateById(paymentInfo);
        //3 根据订单号得到订单信息
        OrderInfo orderInfo = orderInfoService.getById(paymentInfo.getOrderId());
        //4 更新订单信息
        orderInfo.setOrderStatus(OrderStatusEnum.PAID.getStatus());
        orderInfoService.updateById(orderInfo);
        //5 调用医院接口，更新订单支付信息
        SignInfoVo signInfoVo = hospitalFeignClient.getSignInfoVo(orderInfo.getHoscode());
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("hoscode", orderInfo.getHoscode());
        reqMap.put("hosRecordId", orderInfo.getHosRecordId());
        reqMap.put("timestamp", HttpRequestHelper.getTimestamp());
        String sign = HttpRequestHelper.getSign(reqMap, signInfoVo.getSignKey());
        reqMap.put("sign", sign);
        JSONObject result = HttpRequestHelper.sendRequest(reqMap, signInfoVo.getApiUrl() + "/order/updatePayStatus");
        if (result.getInteger("code") != 200) {
            throw new YyghException(result.getString("message"), ResultCodeEnum.FAIL.getCode());
        }
    }

    /**
     * 获取支付记录
     * @param orderId orderId
     * @param paymentType paymentType
     * @return 支付记录
     */
    @Override
    public PaymentInfo getPaymentInfo(Long orderId, Integer paymentType) {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        queryWrapper.eq(PAYMENT_TYPE, paymentType);
        return paymentInfoMapper.selectOne(queryWrapper);
    }
}




