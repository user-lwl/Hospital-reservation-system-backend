package com.lwl.yygh.hosp.service;

import com.lwl.yygh.model.hosp.Hospital;

import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 9:06
 */
public interface HospitalService {

    /**
     * 上传医院接口
     * @param paramMap 医院信息
     */
    void save(Map<String, Object> paramMap);

    /**
     * 根据编号查询医院信息
     * @param hoscode 医院编号
     * @return 医院信息
     */
    Hospital getByHoscode(String hoscode);
}
