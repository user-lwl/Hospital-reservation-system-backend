package com.lwl.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.user.Patient;

import java.util.List;

/**
* @author user-lwl
* @description 针对表【patient(就诊人表)】的数据库操作Service
* @createDate 2022-11-23 19:16:33
*/
public interface PatientService extends IService<Patient> {

    /**
     * 获取就诊人列表
     * @param userId userId
     * @return 就诊人列表
     */
    List<Patient> findAllUserId(Long userId);

    /**
     * 根据id获取就诊人信息
     * @param id id
     * @return 就诊人信息
     */
    Patient getPatientId(Long id);
}
