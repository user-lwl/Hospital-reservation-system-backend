package com.lwl.yygh.hosp.receive;

import com.lwl.common.rabbit.constant.MqConst;
import com.lwl.common.rabbit.service.RabbitService;
import com.lwl.yygh.hosp.service.ScheduleService;
import com.lwl.yygh.model.hosp.Schedule;
import com.lwl.yygh.vo.msm.MsmVo;
import com.lwl.yygh.vo.order.OrderMqVo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HospitalReceiver {

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private RabbitService rabbitService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_ORDER, durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_ORDER),
            key = {MqConst.ROUTING_ORDER}
    ))
    public void receiver(OrderMqVo orderMqVo, Message message, Channel channel) {
        Schedule schedule = scheduleService.getScheduleById(orderMqVo.getScheduleId());
        if (orderMqVo.getAvailableNumber() != null) {
            //下单成功更新预约数
            schedule.setReservedNumber(orderMqVo.getReservedNumber());
            schedule.setAvailableNumber(orderMqVo.getAvailableNumber());
        } else {
            int availableNumber = schedule.getAvailableNumber() + 1;
            schedule.setAvailableNumber(availableNumber);
        }
        scheduleService.update(schedule);
        //发送短信
        MsmVo msmVo = orderMqVo.getMsmVo();
        if(null != msmVo) {
            rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_MSM, MqConst.ROUTING_MSM_ITEM, msmVo);
        }
    }
}
