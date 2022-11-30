package com.lwl.yygh.order.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.common.utils.AuthContextHolder;
import com.lwl.yygh.enums.OrderStatusEnum;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.order.service.OrderInfoService;
import com.lwl.yygh.vo.order.OrderCountQueryVo;
import com.lwl.yygh.vo.order.OrderQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据订单id查询订单详情
     * @param orderId 订单id
     * @return 订单详情
     */
    @GetMapping("/auth/getOrders/{orderId}")
    public Result<OrderInfo> getOrders(@PathVariable String orderId) {
        OrderInfo orderInfo = orderInfoService.getOrder(orderId);
        return Result.ok(orderInfo);
    }

    /**
     * 获取订单列表（条件查询带分页）
     * @param page 当前页
     * @param limit 每页记录数
     * @param orderQueryVo orderQueryVo
     * @return 订单列表分页信息
     */
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result<IPage<OrderInfo>> index(@PathVariable Long page,
                        @PathVariable Long limit,
                        OrderQueryVo orderQueryVo,
                        HttpServletRequest request) {
        orderQueryVo.setUserId(AuthContextHolder.getUserId(request));
        Page<OrderInfo> pageParam = new Page<>(page, limit);
        IPage<OrderInfo> pageModel = orderInfoService.selectPage(pageParam, orderQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 获取订单状态
     * @return List
     */
    @ApiOperation(value = "获取订单状态")
    @GetMapping("/getStatusList")
    public Result<List<Map<String,Object>>> getStatusList() {
        return Result.ok(OrderStatusEnum.getStatusList());
    }

    /**
     * 取消预约
     * @param orderId 订单id
     * @return 是否成功
     */
    @ApiOperation(value = "取消预约")
    @GetMapping("/auth/cancelOrder/{orderId}")
    public Result<Boolean> cancelOrder(@PathVariable("orderId") Long orderId) {
        return Result.ok(orderInfoService.cancelOrder(orderId));
    }

    /**
     * 获取订单统计数据
     * @param orderCountQueryVo orderCountQueryVo
     * @return 订单统计数据
     */
    @ApiOperation(value = "获取订单统计数据")
    @PostMapping("inner/getCountMap")
    public Map<String, Object> getCountMap(@RequestBody OrderCountQueryVo orderCountQueryVo) {
        return orderInfoService.getCountMap(orderCountQueryVo);
    }

}
