package com.lwl.yygh.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.vo.order.OrderCountQueryVo;
import com.lwl.yygh.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author user-lwl
* @description 针对表【order_info(订单表)】的数据库操作Mapper
* @createDate 2022-11-23 19:16:15
*/
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 查询预约统计数据的方法
     * @param orderCountQueryVo orderCountQueryVo
     * @return 预约统计数据
     */
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}




