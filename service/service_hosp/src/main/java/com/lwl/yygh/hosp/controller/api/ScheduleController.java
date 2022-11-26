package com.lwl.yygh.hosp.controller.api;

import com.lwl.yygh.common.helper.HttpRequestHelper;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.hosp.service.ScheduleService;
import com.lwl.yygh.model.hosp.Schedule;
import com.lwl.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 15:29
 */
@RestController
@RequestMapping("/api/hosp")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    /**
     * 上传排班接口
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        //传递科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        scheduleService.save(paramMap);
        return Result.ok();
    }

    /**
     * 查询排班接口
     * @param request 请求
     * @return Page
     */
    @PostMapping("/schedule/list")
    public Result<Page<Schedule>> findSchedule(HttpServletRequest request) {
        //传递科室信息
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        //医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //科室编号
        String depcode = (String) paramMap.get("depcode");
        //当前页
        Integer page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : (Integer) paramMap.get("page");
        Integer limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : (Integer) paramMap.get("limit");
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page, limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 删除排班
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/schedule/remove")
    public Result remove(HttpServletRequest request) {
        //传递科室信息
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
        String hoscode = (String) paramMap.get("hoscode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        scheduleService.remove(hoscode, hosScheduleId);
        return Result.ok();
    }
}
