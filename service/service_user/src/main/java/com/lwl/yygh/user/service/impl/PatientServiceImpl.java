package com.lwl.yygh.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.model.user.Patient;
import com.lwl.yygh.user.mapper.PatientMapper;
import com.lwl.yygh.user.service.PatientService;
import org.springframework.stereotype.Service;

/**
* @author user-lwl
* @description 针对表【patient(就诊人表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:33
*/
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient>
    implements PatientService {

}




