package com.lwl.yygh.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.model.order.PaymentInfo;
import com.lwl.yygh.order.mapper.PaymentInfoMapper;
import com.lwl.yygh.order.service.PaymentInfoService;
import org.springframework.stereotype.Service;

/**
* @author user-lwl
* @description 针对表【payment_info(支付信息表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:19
*/
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
    implements PaymentInfoService {

}




