package com.lwl.yygh.hosp.controller;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.hosp.service.DepartmentService;
import com.lwl.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author user-lwl
 * @createDate 2022/11/27 14:30
 */
@RestController
@RequestMapping("/admin/hosp/department")
//@CrossOrigin
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    /**
     * 查询医院所有科室的列表
     * @param hoscode 医院编号
     * @return 科室列表
     */
    @ApiOperation("查询医院所有科室的列表")
    @GetMapping("/getDeptList/{hoscode}")
    public Result<List<DepartmentVo>> getDeptList (@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }
}
