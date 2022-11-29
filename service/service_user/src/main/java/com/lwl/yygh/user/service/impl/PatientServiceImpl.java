package com.lwl.yygh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.cmn.client.DictFeignClient;
import com.lwl.yygh.enums.DictEnum;
import com.lwl.yygh.model.user.Patient;
import com.lwl.yygh.user.mapper.PatientMapper;
import com.lwl.yygh.user.service.PatientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author user-lwl
* @description 针对表【patient(就诊人表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:33
*/
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient>
    implements PatientService {
    @Resource
    private PatientMapper patientMapper;

    @Resource
    private DictFeignClient dictFeignClient;

    /**
     * 获取就诊人列表
     * @param userId userId
     * @return 就诊人列表
     */
    @Override
    public List<Patient> findAllUserId(Long userId) {
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Patient> patientList = patientMapper.selectList(queryWrapper);
        patientList.forEach(this::packPatient);
        return patientList;
    }

    /**
     * 根据id获取就诊人信息
     * @param id id
     * @return 就诊人信息
     */
    @Override
    public Patient getPatientId(Long id) {
        Patient patient = patientMapper.selectById(id);
        return this.packPatient(patient);
    }

    /**
     * Patient其他参数封装
     * @param patient Patient
     */
    private Patient packPatient(Patient patient) {
        String certificatesTypeString = dictFeignClient.getName(DictEnum.CERTIFICATES_TYPE.getDictCode(), patient.getCertificatesType());
        //联系人证件类型
        String contactsCertificatesTypeString = dictFeignClient.getName(DictEnum.CERTIFICATES_TYPE.getDictCode(),patient.getContactsCertificatesType());
        //省
        String provinceString = dictFeignClient.getName(patient.getProvinceCode());
        //市
        String cityString = dictFeignClient.getName(patient.getCityCode());
        //区
        String districtString = dictFeignClient.getName(patient.getDistrictCode());
        patient.getParam().put("certificatesTypeString", certificatesTypeString);
        patient.getParam().put("contactsCertificatesTypeString", contactsCertificatesTypeString);
        patient.getParam().put("provinceString", provinceString);
        patient.getParam().put("cityString", cityString);
        patient.getParam().put("districtString", districtString);
        patient.getParam().put("fullAddress", provinceString + cityString + districtString + patient.getAddress());
        return patient;
    }
}




