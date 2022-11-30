package com.lwl.yygh.order.service;

import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/30 13:36
 */
public interface WeiXinService {
    /**
     * 生成微信支付二维码
     * @param orderId 订单id
     * @return result
     */
    Map<String, Object> createNative(Long orderId);

    /**
     * 查询支付状态
     * @param orderId 订单id
     * @return Map
     */
    Map<String, String> queryPayStatus(Long orderId);

    /***
     * 退款
     * @param orderId 订单id
     * @return 是否成功
     */
    Boolean refund(Long orderId);

}
