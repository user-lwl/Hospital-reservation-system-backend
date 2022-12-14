package com.lwl.yygh.hosp.service;

import com.lwl.yygh.model.hosp.Schedule;
import com.lwl.yygh.vo.hosp.ScheduleOrderVo;
import com.lwl.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 15:27
 */
public interface ScheduleService {
    /**
     * 上传排班接口
     * @param paramMap 参数
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询排班接口
     * @param page 当前页
     * @param limit 每页条数
     * @param scheduleQueryVo scheduleVO
     * @return Page
     */
    Page<Schedule> findPageSchedule(Integer page, Integer limit, ScheduleQueryVo scheduleQueryVo);

    /**
     * 删除排班接口
     * @param hoscode 医院编号
     * @param hosScheduleId 排班编号
     */
    void remove(String hoscode, String hosScheduleId);

    /**
     * 查询排班规则数据
     * @param page 当前页
     * @param limit 一页几条数据
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @return 排班规则数据
     */
    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    /**
     * 查询排班详细信息
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @param workDate 日期
     * @return 排班信息
     */
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

    /**
     * 获取可预约的排班数据
     * @param page 当前页
     * @param limit 每页记录数
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @return 可预约排班数据
     */
    Map<String, Object> getBookingScheduleRule(Integer page, Integer limit, String hoscode, String depcode);

    /**
     * 根据排班id获取排班数据
     * @param scheduleId 排班id
     * @return 排班数据
     */
    Schedule getScheduleById(String scheduleId);

    /**
     * 根据排班id获取预约下单数据
     * @param scheduleId 排班id
     * @return 预约下单数据
     */
    ScheduleOrderVo getScheduleOrderVo(String scheduleId);

    /**
     * 更新排班数据
     * @param schedule Schedule
     */
    void update(Schedule schedule);
}
