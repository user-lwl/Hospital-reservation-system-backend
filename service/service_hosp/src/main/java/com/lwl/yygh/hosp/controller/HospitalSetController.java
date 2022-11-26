package com.lwl.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.common.utils.MD5;
import com.lwl.yygh.hosp.service.HospitalSetService;
import com.lwl.yygh.model.hosp.HospitalSet;
import com.lwl.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author user-lwl
 * @createDate 2022/11/24 14:11
 */
@Api(tags = "医院设置")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Resource
    private HospitalSetService hospitalSetService;

    /**
     * 查询医院设置所有信息
     *
     * @return 医院设置所有信息
     */
    @GetMapping("/findAll")
    @ApiOperation("获取所有医院设置信息")
    public Result<List<HospitalSet>> findAllHospitalSet() {
        return Result.ok(hospitalSetService.list());
    }

    /**
     * 逻辑删除医院设置
     *
     * @param id id
     * @return 是否删除成功
     */
    @DeleteMapping("{id}")
    @ApiOperation("逻辑删除医院设置")
    public Result removeHospSet(@PathVariable Long id) {
        if (hospitalSetService.removeById(id)) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 条件查询带分页
     *
     * @param current            当前页
     * @param limit              每页记录数
     * @param hospitalSetQueryVo 请求实体
     * @return 查询结果
     */
    @ApiOperation("分页查询医院")
    @PostMapping("/findPageHospSet/{current}/{limit}")
    public Result<Page<HospitalSet>> findPageHospSet(@PathVariable long current,
                                                     @PathVariable long limit,
                                                     @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        Page<HospitalSet> page = new Page<>(current, limit);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();
        String hoscode = hospitalSetQueryVo.getHoscode();
        if (StringUtils.isNotBlank(hosname)) {
            wrapper.like("hosname", hosname);
        }
        if (StringUtils.isNotBlank(hoscode)) {
            wrapper.eq("hoscode", hoscode);
        }
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);
        return Result.ok(pageHospitalSet);
    }

    /**
     * 添加医院设置
     * @param hospitalSet 医院设置
     * @return 是否成功
     */
    @ApiOperation("添加医院设置")
    @PostMapping("/saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        // 1 能使用 0 不能使用
        hospitalSet.setStatus(1);
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 根据id获取医院设置
     * @param id id
     * @return 医院设置信息
     */
    @GetMapping("/getHospSet/{id}")
    @ApiOperation("根据id获取医院设置")
    public Result<HospitalSet> getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    /**
     * 根据id修改医院设置
     * @param hospitalSet 医院信息
     * @return 是否成功
     */
    @PostMapping("/updateHospitalSet")
    @ApiOperation("修改医院设置")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean b = hospitalSetService.updateById(hospitalSet);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 批量删除医院设置
     * @param idList id列表
     * @return 是否成功
     */
    @DeleteMapping("/batchRemove")
    @ApiOperation("批量删除医院设置")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

    /**
     * 医院状态更改，锁定/解锁
     * @param id id
     * @param status 状态
     * @return 是否成功
     */
    @PutMapping("/lockHospitalSet/{id}/{status}")
    @ApiOperation("变更医院状态")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        hospitalSetService.updateById(hospitalSet);
        return Result.ok();
    }

    @PutMapping("sendKey/{id}")
    @ApiOperation("发送密钥")
    public Result sendKey(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        // TODO 短信验证码
        System.out.println(signKey + hoscode);
        return Result.ok();
    }

}
