package com.lwl.yygh.order.client;

import com.lwl.yygh.vo.order.OrderCountQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/30 19:50
 */
@FeignClient("service-order")
@Repository
public interface OrderFeignClient {
    /**
     * 获取订单统计数据
     * @param orderCountQueryVo orderCountQueryVo
     * @return 订单统计数据
     */
    @PostMapping("/api/order/orderInfo/inner/getCountMap")
    Map<String, Object> getCountMap(@RequestBody OrderCountQueryVo orderCountQueryVo);
}
