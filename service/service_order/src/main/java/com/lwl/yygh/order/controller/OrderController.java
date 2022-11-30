package com.lwl.yygh.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.enums.OrderStatusEnum;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.order.service.OrderInfoService;
import com.lwl.yygh.vo.order.OrderQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "订单接口")
@RestController
@RequestMapping("/admin/order/orderInfo")
public class OrderController {

    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 获取分页列表
     * @param page 当前页
     * @param limit 每页信息条数
     * @param orderQueryVo orderQueryVo
     * @return 分页列表
     */
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result<IPage<OrderInfo>> index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "orderCountQueryVo", value = "查询对象") OrderQueryVo orderQueryVo) {
        Page<OrderInfo> pageParam = new Page<>(page, limit);
        IPage<OrderInfo> pageModel = orderInfoService.selectPage(pageParam, orderQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 获取订单状态
     * @return 订单状态
     */
    @ApiOperation(value = "获取订单状态")
    @GetMapping("getStatusList")
    public Result<List<Map<String,Object>>> getStatusList() {
        return Result.ok(OrderStatusEnum.getStatusList());
    }
}
