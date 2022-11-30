package com.lwl.yygh.task.scheduled;

import com.lwl.common.rabbit.constant.MqConst;
import com.lwl.common.rabbit.service.RabbitService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class ScheduledTask {

    @Resource
    private RabbitService rabbitService;

    /**
     * 每天8点执行 提醒就诊
     */
    @Scheduled(cron = "0 0 8 * * ?")
//    @Scheduled(cron = "0/30 * * * * ?")
    public void task1() {
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK, MqConst.ROUTING_TASK_8, "");
    }
}
