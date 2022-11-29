package com.lwl.yygh.hosp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.yygh.model.hosp.HospitalSet;
import com.lwl.yygh.vo.order.SignInfoVo;

/**
* @author user-lwl
* @description 针对表【hospital_set(医院设置表)】的数据库操作Service
* @createDate 2022-11-23 19:16:02
*/
public interface HospitalSetService extends IService<HospitalSet> {

    /**
     * get签名
     * @param hoscode 医院编号
     * @return 签名
     */
    String getSignKey(String hoscode);

    /**
     * 获取医院签名信息
     * @param hoscode 医院编号
     * @return 医院签名信息
     */
    SignInfoVo getSignInfoVo(String hoscode);
}
