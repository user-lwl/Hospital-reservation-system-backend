package com.lwl.yygh.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.order.OrderInfo;

/**
* @author user-lwl
* @description 针对表【order_info(订单表)】的数据库操作Service
* @createDate 2022-11-23 19:16:15
*/
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 生成挂号订单
     * @param scheduleId 排班id
     * @param patientId 就诊人id
     * @return 订单id
     */
    Long saveOrder(String scheduleId, Long patientId);
}
