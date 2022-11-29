package com.lwl.yygh.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.yygh.common.exception.YyghException;
import com.lwl.yygh.common.result.ResultCodeEnum;
import com.lwl.yygh.hosp.mapper.HospitalSetMapper;
import com.lwl.yygh.hosp.service.HospitalSetService;
import com.lwl.yygh.model.hosp.HospitalSet;
import com.lwl.yygh.vo.order.SignInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author user-lwl
* @description 针对表【hospital_set(医院设置表)】的数据库操作Service实现
* @createDate 2022-11-23 19:16:02
*/
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet>
    implements HospitalSetService {

    @Resource
    private HospitalSetMapper hospitalSetMapper;

    /**
     * get签名
     * @param hoscode 医院编号
     * @return 签名
     */
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hoscode);
        return hospitalSetMapper.selectOne(wrapper).getSignKey();
    }

    /**
     * 获取医院签名信息
     * @param hoscode 医院编号
     * @return 医院签名信息
     */
    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new YyghException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;
    }
}




