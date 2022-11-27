package com.lwl.yygh.hosp.controller;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.hosp.service.ScheduleService;
import com.lwl.yygh.model.hosp.Schedule;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/27 14:56
 */
@RestController
@RequestMapping("/admin/hosp/schedule")
//@CrossOrigin
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    /**
     * 查询排班规则数据
     * @param page 当前页
     * @param limit 每页几条数据
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @return 数据
     */
    @ApiOperation("查询排班的规则数据")
    @GetMapping("/getScheduleRule/{page}/{limit}/{hoscode}/{depcode}")
    public Result<Map<String, Object>> getScheduleRule(@PathVariable long page,
                                  @PathVariable long limit,
                                  @PathVariable String hoscode,
                                  @PathVariable String depcode) {
        Map<String, Object> map = scheduleService.getRuleSchedule(page, limit, hoscode, depcode);
        return Result.ok(map);
    }

    /**
     * 查询排班详细信息
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @param workDate 日期
     * @return 排班信息
     */
    @ApiOperation("查询排班详细信息")
    @GetMapping("getScheduleDetail/{hoscode}/{depcode}/{workDate}")
    public Result<List<Schedule>> getScheduleDetail(@PathVariable String hoscode,
                                    @PathVariable String depcode,
                                    @PathVariable String workDate) {
        List<Schedule> list = scheduleService.getDetailSchedule(hoscode, depcode, workDate);
        return Result.ok(list);
    }
}
