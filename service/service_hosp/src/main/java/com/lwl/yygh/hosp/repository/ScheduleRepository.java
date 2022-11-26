package com.lwl.yygh.hosp.repository;

import com.lwl.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author user-lwl
 * @createDate 2022/11/26 15:26
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    /**
     * 查询排班
     * @param hoscode 医院编号
     * @param hosScheduleId 排班编号
     * @return Schedule
     */
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
