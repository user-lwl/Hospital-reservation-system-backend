package com.lwl.yygh.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.model.order.PaymentInfo;

import java.util.Map;

/**
* @author user-lwl
* @description 针对表【payment_info(支付信息表)】的数据库操作Service
* @createDate 2022-11-23 19:16:19
*/
public interface PaymentInfoService extends IService<PaymentInfo> {

    /**
     * 保存交易记录
     * @param orderInfo OrderInfo
     * @param paymentType 支付类型（1：微信 2：支付宝）
     */
    void savePaymentInfo(OrderInfo orderInfo, Integer paymentType);

    /**
     * 更新订单状态
     * @param outTradeNo 订单编号
     * @param resultMap 订单信息
     */
    void paySuccess(String outTradeNo, Map<String, String> resultMap);

    /**
     * 获取支付记录
     * @param orderId orderId
     * @param paymentType paymentType
     * @return 支付记录
     */
    PaymentInfo getPaymentInfo(Long orderId, Integer paymentType);
}
