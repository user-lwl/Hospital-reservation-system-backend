package com.lwl.yygh.hosp.controller.api;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.hosp.service.DepartmentService;
import com.lwl.yygh.hosp.service.HospitalService;
import com.lwl.yygh.model.hosp.Hospital;
import com.lwl.yygh.vo.hosp.DepartmentVo;
import com.lwl.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
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
}
