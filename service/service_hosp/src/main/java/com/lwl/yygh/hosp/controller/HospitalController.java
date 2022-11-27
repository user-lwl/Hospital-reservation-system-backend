package com.lwl.yygh.hosp.controller;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.hosp.service.HospitalService;
import com.lwl.yygh.model.hosp.Hospital;
import com.lwl.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/27 9:14
 */
@RestController
@RequestMapping("/admin/hosp/hospital")
//@CrossOrigin
public class HospitalController {
    @Resource
    private HospitalService hospitalService;

    /**
     * 分页查询医院列表
     * @param page 当前页
     * @param limit 每页条数
     * @param hospitalQueryVo hospitalQueryVo
     * @return 分页查询结果
     */
    @GetMapping("/list/{page}/{limit}")
    public Result<Page<Hospital>> listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 更新医院的上线状态
     * @param id id
     * @param status 状态
     * @return 是否成功
     */
    @ApiOperation("更新医院的上线状态")
    @GetMapping("/updateHospStatus/{id}/{status}")
    public Result updateHospStatus(@PathVariable String id,
                                   @PathVariable Integer status) {
        hospitalService.updateStatus(id, status);
        return Result.ok();
    }

    /**
     * 医院详情信息
     * @param id id
     * @return 医院信息
     */
    @ApiOperation("医院详情信息")
    @GetMapping("/showHospDetail/{id}")
    public Result<Map<String, Object>> showHospDetail(@PathVariable String id) {
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }
}
