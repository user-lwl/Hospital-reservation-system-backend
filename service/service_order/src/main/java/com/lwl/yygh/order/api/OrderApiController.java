package com.lwl.yygh.order.api;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.order.service.OrderInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author user-lwl
 * @createDate 2022/11/29 15:30
 */
@RestController
@RequestMapping("/api/order/orderInfo")
public class OrderApiController {
    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 生成挂号订单
     * @param scheduleId 排班id
     * @param patientId 就诊人id
     * @return 订单id
     */
    @PostMapping("/auth/submitOrder/{scheduleId}/{patientId}")
    public Result<Long> saveOrders(@PathVariable String scheduleId,
                             @PathVariable Long patientId) {
        Long orderId = orderInfoService.saveOrder(scheduleId, patientId);
        return Result.ok(orderId);
    }
}
