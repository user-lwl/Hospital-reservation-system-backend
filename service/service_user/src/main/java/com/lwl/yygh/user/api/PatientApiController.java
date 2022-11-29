package com.lwl.yygh.user.api;

import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.common.utils.AuthContextHolder;
import com.lwl.yygh.model.user.Patient;
import com.lwl.yygh.user.service.PatientService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 就诊人接口
 * @author user-lwl
 * @createDate 2022/11/29 9:29
 */
@RestController
@RequestMapping("/api/user/patient")
public class PatientApiController {
    @Resource
    private PatientService patientService;

    /**
     * 获取就诊人列表
     * @param request 请求
     * @return 就诊人列表
     */
    @GetMapping("/auth/findAll")
    public Result<List<Patient>> findAll(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        List<Patient> list = patientService.findAllUserId(userId);
        return Result.ok(list);
    }

    /**
     * 添加就诊人信息
     * @param patient 信息
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/auth/save")
    public Result<Boolean> savePatient(@RequestBody Patient patient, HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId(request);
        patient.setUserId(userId);
        patientService.save(patient);
        return Result.ok();
    }

    /**
     * 根据id获取就诊人信息
     * @param id id
     * @return 就诊人信息
     */
    @GetMapping("/auth/get/{id}")
    public Result<Patient> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientId(id);
        return Result.ok(patient);
    }

    /**
     * 修改就诊人信息
     * @param patient 就诊人信息
     * @return 是否成功
     */
    @PostMapping("/auth/update")
    public Result<Boolean> updatePatient(@RequestBody Patient patient) {
        patientService.updateById(patient);
        return Result.ok();
    }

    /**
     * 删除就诊人信息
     * @param id id
     * @return 是否成功
     */
    @DeleteMapping("/auth/remove/{id}")
    public Result<Boolean> removePatient(@PathVariable Long id) {
        patientService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据就诊人id获取就诊人信息
     * @param id 就诊人id
     * @return 就诊人信息
     */
    @GetMapping("/inner/get/{id}")
    public Patient getPatientOrder(@PathVariable Long id) {
        return patientService.getPatientId(id);
    }
}
