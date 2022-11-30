package com.lwl.yygh.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.lwl.yygh.enums.PaymentTypeEnum;
import com.lwl.yygh.enums.RefundStatusEnum;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.model.order.PaymentInfo;
import com.lwl.yygh.model.order.RefundInfo;
import com.lwl.yygh.order.service.OrderInfoService;
import com.lwl.yygh.order.service.PaymentInfoService;
import com.lwl.yygh.order.service.RefundInfoService;
import com.lwl.yygh.order.service.WeiXinService;
import com.lwl.yygh.order.utils.ConstantPropertiesUtils;
import com.lwl.yygh.order.utils.HttpClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author user-lwl
 * @createDate 2022/11/30 13:36
 */
@Service
public class WeiXinServiceImpl implements WeiXinService {
    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private RedisTemplate<String, Map<String, Object>> redisTemplate;

    @Resource
    private PaymentInfoService paymentInfoService;

    @Resource
    private RefundInfoService refundInfoService;

    public static final String APPID = "appid";

    public static final String MCH_ID = "mch_id";

    public static final String NONCE_STR = "nonce_str";

    public static final String OUT_TRADE_NO = "out_trade_no";

    public static final String RESULT_CODE = "result_code";

    @Override
    public Map<String, Object> createNative(Long orderId) {
        try {
            Map<String, Object> payMap = redisTemplate.opsForValue().get(orderId.toString());
            if(null != payMap) {
                return payMap;
            }
            //根据id获取订单信息
            OrderInfo orderInfo = orderInfoService.getById(orderId);
            // 保存交易记录
            paymentInfoService.savePaymentInfo(orderInfo, PaymentTypeEnum.WEIXIN.getStatus());
            //1、设置参数
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(APPID, ConstantPropertiesUtils.APPID);
            paramMap.put(MCH_ID, ConstantPropertiesUtils.PARTNER);
            paramMap.put(NONCE_STR, WXPayUtil.generateNonceStr());
            String body = orderInfo.getReserveDate() + "就诊"+ orderInfo.getDepname();
            paramMap.put("body", body);
            paramMap.put(OUT_TRADE_NO, orderInfo.getOutTradeNo());
            //paramMap.put("total_fee", order.getAmount().multiply(new BigDecimal("100")).longValue()+"");
            paramMap.put("total_fee", "1");
            paramMap.put("spbill_create_ip", "127.0.0.1");
            paramMap.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");
            paramMap.put("trade_type", "NATIVE");
            //2、HTTPClient来根据URL访问第三方接口并且传递参数
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //client设置参数
            client.setXmlParam(WXPayUtil.generateSignedXml(paramMap, ConstantPropertiesUtils.PARTNERKEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //4、封装返回结果集
            Map<String, Object> map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("totalFee", orderInfo.getAmount());
            map.put("resultCode", resultMap.get(RESULT_CODE));
            map.put("codeUrl", resultMap.get("code_url"));
            if(null != resultMap.get(RESULT_CODE)) {
                //微信支付二维码2小时过期，可采取2小时未支付取消订单
                redisTemplate.opsForValue().set(orderId.toString(), map, 1000, TimeUnit.MINUTES);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * 查询支付状态
     * @param orderId 订单id
     * @return Map
     */
    @Override
    public Map<String, String> queryPayStatus(Long orderId) {
        try {
            OrderInfo orderInfo = orderInfoService.getById(orderId);
            //1、封装参数
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(APPID, ConstantPropertiesUtils.APPID);
            paramMap.put(MCH_ID, ConstantPropertiesUtils.PARTNER);
            paramMap.put(OUT_TRADE_NO, orderInfo.getOutTradeNo());
            paramMap.put(NONCE_STR, WXPayUtil.generateNonceStr());
            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(paramMap, ConstantPropertiesUtils.PARTNERKEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据，转成Map
            String xml = client.getContent();
            return WXPayUtil.xmlToMap(xml);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 退款
     * @param orderId 订单id
     * @return 是否成功
     */
    @Override
    public Boolean refund(Long orderId) {
        try {
            PaymentInfo paymentInfoQuery = paymentInfoService.getPaymentInfo(orderId, PaymentTypeEnum.WEIXIN.getStatus());
            RefundInfo refundInfo = refundInfoService.saveRefundInfo(paymentInfoQuery);
            // 判断是否退款
            if(refundInfo.getRefundStatus().intValue() == RefundStatusEnum.REFUND.getStatus().intValue()) {
                return true;
            }
            Map<String,String> paramMap = new HashMap<>(8);
            paramMap.put(APPID,ConstantPropertiesUtils.APPID);       //公众账号ID
            paramMap.put(MCH_ID,ConstantPropertiesUtils.PARTNER);   //商户编号
            paramMap.put(NONCE_STR,WXPayUtil.generateNonceStr());
            paramMap.put("transaction_id",paymentInfoQuery.getTradeNo()); //微信订单号
            paramMap.put(OUT_TRADE_NO,paymentInfoQuery.getOutTradeNo()); //商户订单编号
            paramMap.put("out_refund_no","tk"+paymentInfoQuery.getOutTradeNo()); //商户退款单号
            // paramMap.put("total_fee",paymentInfoQuery.getTotalAmount().multiply(new BigDecimal("100")).longValue()+"");
            // paramMap.put("refund_fee",paymentInfoQuery.getTotalAmount().multiply(new BigDecimal("100")).longValue()+"");
            paramMap.put("total_fee","1");
            paramMap.put("refund_fee","1");
            String paramXml = WXPayUtil.generateSignedXml(paramMap,ConstantPropertiesUtils.PARTNERKEY);
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/secapi/pay/refund");
            client.setXmlParam(paramXml);
            client.setHttps(true);
            client.setCert(true);
            client.setCertPassword(ConstantPropertiesUtils.PARTNER);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            if (WXPayConstants.SUCCESS.equalsIgnoreCase(resultMap.get(RESULT_CODE))) {
                refundInfo.setCallbackTime(new Date());
                refundInfo.setTradeNo(resultMap.get("refund_id"));
                refundInfo.setRefundStatus(RefundStatusEnum.REFUND.getStatus());
                refundInfo.setCallbackContent(JSON.toJSONString(resultMap));
                refundInfoService.updateById(refundInfo);
                return true;
            }
            return false;
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
