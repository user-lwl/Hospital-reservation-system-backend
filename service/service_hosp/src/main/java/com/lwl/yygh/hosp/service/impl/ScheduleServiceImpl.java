package com.lwl.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSON;
import com.lwl.yygh.hosp.repository.ScheduleRepository;
import com.lwl.yygh.hosp.service.ScheduleService;
import com.lwl.yygh.model.hosp.Schedule;
import com.lwl.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 15:27
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    private ScheduleRepository scheduleRepository;

    /**
     * 上传排班
     * @param paramMap 参数
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        Schedule schedule = JSON.parseObject(JSON.toJSONString(paramMap), Schedule.class);
        Schedule scheduleExist = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(), schedule.getHosScheduleId());
        if (scheduleExist == null) {
            schedule.setCreateTime(new Date());
        }
        schedule.setUpdateTime(new Date());
        schedule.setIsDeleted(0);
        schedule.setStatus(1);
        scheduleRepository.save(schedule);
    }

    /**
     * 查询排班
     * @param page 当前页
     * @param limit 每页条数
     * @param scheduleQueryVo scheduleVO
     * @return Page
     */
    @Override
    public Page<Schedule> findPageSchedule(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo) {
        //Pageable
        Pageable pageable = PageRequest.of(page - 1, limit);
        //Example
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleQueryVo, schedule);
        schedule.setIsDeleted(0);
        schedule.setStatus(1);
        Example<Schedule> example = Example.of(schedule, matcher);
        return scheduleRepository.findAll(example, pageable);

    }

    /**
     * 删除排班
     * @param hoscode 医院编号
     * @param hosScheduleId 排班编号
     */
    @Override
    public void remove(String hoscode, String hosScheduleId) {
        Schedule schedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
        if (schedule != null) {
            scheduleRepository.deleteById(schedule.getId());
        }
    }
}
