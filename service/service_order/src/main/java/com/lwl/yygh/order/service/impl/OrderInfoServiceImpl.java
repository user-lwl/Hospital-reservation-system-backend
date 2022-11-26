package com.lwl.yygh.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.model.order.OrderInfo;
import com.lwl.yygh.order.mapper.OrderInfoMapper;
import com.lwl.yygh.order.service.OrderInfoService;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【order_info(订单表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:15
*/
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>
    implements OrderInfoService {

}




