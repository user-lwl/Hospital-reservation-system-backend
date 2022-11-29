package com.lwl.yygh.hosp.controller.api;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.hosp.service.DepartmentService;
import com.lwl.yygh.hosp.service.HospitalService;
import com.lwl.yygh.hosp.service.HospitalSetService;
import com.lwl.yygh.hosp.service.ScheduleService;
import com.lwl.yygh.model.hosp.Hospital;
import com.lwl.yygh.model.hosp.Schedule;
import com.lwl.yygh.vo.hosp.DepartmentVo;
import com.lwl.yygh.vo.hosp.HospitalQueryVo;
import com.lwl.yygh.vo.hosp.ScheduleOrderVo;
import com.lwl.yygh.vo.order.SignInfoVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/27 19:57
 */
@RestController
@RequestMapping("/api/hosp/hospital")
public class ApiHospitalController {
    @Resource
    private HospitalService hospitalService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private HospitalSetService hospitalSetService;

    /**
     * 分页查询医院列表
     * @param page 当前页
     * @param limit 每页条数
     * @param hospitalQueryVo hospitalQueryVo
     * @return 医院分页信息
     */
    @ApiOperation("查询医院列表")
    @GetMapping("/findHospList/{page}/{limit}")
    public Result<Page<Hospital>> findHospList(@PathVariable Integer page,
                               @PathVariable Integer limit,
                               HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> hospitals = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(hospitals);
    }

    /**
     * 根据医院名称查询
     * @param hosname 医院名称
     * @return 医院列表
     */
    @ApiOperation("根据医院名称查询")
    @GetMapping("/findByHosName/{hosname}")
    public Result<List<Hospital>> findByHosName(@PathVariable String hosname) {
        List<Hospital> list = hospitalService.findByHosname(hosname);
        return Result.ok(list);
    }

    /**
     * 根据医院编号获取科室信息
     * @param hoscode 医院编号
     * @return 科室信息
     */
    @ApiOperation("根据医院编号获取科室信息")
    @GetMapping("/department/{hoscode}")
    public Result<List<DepartmentVo>> index(@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }

    /**
     * 根据医院编号获取医院挂号详情
     * @param hoscode 医院编号
     * @return 挂号详情
     */
    @ApiOperation("根据医院编号获取医院挂号详情")
    @GetMapping("/findHospDetail/{hoscode}")
    public Result<Map<String, Object>> item(@PathVariable String hoscode) {
        Map<String, Object> map = hospitalService.item(hoscode);
        return Result.ok(map);
    }

    /**
     * 获取可预约排班数据
     * @param page 当前页
     * @param limit 每页记录数
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @return 可预约排班数据
     */
    @ApiOperation(value = "获取可预约排班数据")
    @GetMapping("auth/getBookingScheduleRule/{page}/{limit}/{hoscode}/{depcode}")
    public Result<Map<String, Object>> getBookingSchedule(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode,
            @ApiParam(name = "depcode", value = "科室code", required = true)
            @PathVariable String depcode) {
        return Result.ok(scheduleService.getBookingScheduleRule(page, limit, hoscode, depcode));
    }

    /**
     * 获取排版数据
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @param workDate 日期
     * @return 排班数据
     */
    @ApiOperation(value = "获取排班数据")
    @GetMapping("auth/findScheduleList/{hoscode}/{depcode}/{workDate}")
    public Result<List<Schedule>> findScheduleList(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable String hoscode,
            @ApiParam(name = "depcode", value = "科室code", required = true)
            @PathVariable String depcode,
            @ApiParam(name = "workDate", value = "排班日期", required = true)
            @PathVariable String workDate) {
        return Result.ok(scheduleService.getDetailSchedule(hoscode, depcode, workDate));
    }

    /**
     * 根据排班id获取排班数据
     * @param scheduleId 排班id
     * @return 排班数据
     */
    @ApiOperation(value = "根据排班id获取排班数据")
    @GetMapping("getSchedule/{scheduleId}")
    public Result<Schedule> getSchedule(@PathVariable String scheduleId) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        return Result.ok(schedule);
    }

    /**
     * 根据排班id获取预约下单数据
     * @param scheduleId 排班id
     * @return 预约下单数据
     */
    @ApiOperation(value = "根据排班id获取预约下单数据")
    @GetMapping("inner/getScheduleOrderVo/{scheduleId}")
    public ScheduleOrderVo getScheduleOrderVo(
            @ApiParam(name = "scheduleId", value = "排班id", required = true)
            @PathVariable("scheduleId") String scheduleId) {
        return scheduleService.getScheduleOrderVo(scheduleId);
    }

    /**
     * 获取医院签名信息
     * @param hoscode 医院编号
     * @return 医院签名信息
     */
    @ApiOperation(value = "获取医院签名信息")
    @GetMapping("inner/getSignInfoVo/{hoscode}")
    public SignInfoVo getSignInfoVo(
            @ApiParam(name = "hoscode", value = "医院code", required = true)
            @PathVariable("hoscode") String hoscode) {
        return hospitalSetService.getSignInfoVo(hoscode);
    }

}
