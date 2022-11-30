package com.lwl.yygh.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.vo.order.OrderCountQueryVo;
import com.lwl.yygh.vo.order.OrderQueryVo;

import java.util.Map;

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

    /**
     * 根据订单id查询订单详情
     * @param orderId 订单id
     * @return 订单详情
     */
    OrderInfo getOrder(String orderId);

    /**
     * 获取订单列表 条件查询带分页
     * @param pageParam Page
     * @param orderQueryVo orderQueryVo
     * @return 订单列表，分页
     */
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    /**
     * 取消订单
     * @param orderId 订单id
     * @return 是否成功
     */
    Boolean cancelOrder(Long orderId);

    /**
     * 就诊提醒
     */
    void patientTips();

    /**
     * 获取订单统计数据
     * @param orderCountQueryVo orderCountQueryVo
     * @return 订单统计数据
     */
    Map<String, Object> getCountMap(OrderCountQueryVo orderCountQueryVo);

}
