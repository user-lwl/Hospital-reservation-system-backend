package com.lwl.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lwl.yygh.hosp.repository.HospitalRepository;
import com.lwl.yygh.hosp.service.HospitalService;
import com.lwl.yygh.model.hosp.Hospital;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 9:06
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalRepository hospitalRepository;

    /**
     * 上传医院
     * @param paramMap 医院信息
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        //map->Hospital
        Hospital hospital = JSON.parseObject(JSON.toJSONString(paramMap), Hospital.class);
        //判断存在
        Hospital hospitalExit = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        //存在，修改
        if (hospitalExit != null) {
            hospital.setStatus(hospitalExit.getStatus());
            hospital.setCreateTime(hospitalExit.getCreateTime());
        } else {
            //不存在，添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
        }
        hospital.setUpdateTime(new Date());
        hospital.setIsDeleted(0);
        hospitalRepository.save(hospital);
    }

    /**
     * 根据医院编号查询信息
     * @param hoscode 医院编号
     * @return 医院信息
     */
    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }
}
