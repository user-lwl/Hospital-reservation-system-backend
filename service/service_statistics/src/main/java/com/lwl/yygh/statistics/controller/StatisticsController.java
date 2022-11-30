package com.lwl.yygh.statistics.controller;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.order.client.OrderFeignClient;
import com.lwl.yygh.vo.order.OrderCountQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "统计管理接口")
@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Resource
    private OrderFeignClient orderFeignClient;

    /**
     * 获取订单统计数据
     * @param orderCountQueryVo orderCountQueryVo
     * @return 订单统计数据
     */
    @ApiOperation(value = "获取订单统计数据")
    @GetMapping("getCountMap")
    public Result<Map<String, Object>> getCountMap(@ApiParam(name = "orderCountQueryVo", value = "查询对象") OrderCountQueryVo orderCountQueryVo) {
        return Result.ok(orderFeignClient.getCountMap(orderCountQueryVo));
    }
}
