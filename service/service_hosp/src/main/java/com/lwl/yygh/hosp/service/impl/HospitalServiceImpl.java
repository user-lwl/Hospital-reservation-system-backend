package com.lwl.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSON;
import com.lwl.yygh.cmn.client.DictFeignClient;
import com.lwl.yygh.hosp.repository.HospitalRepository;
import com.lwl.yygh.hosp.service.HospitalService;
import com.lwl.yygh.model.hosp.Hospital;
import com.lwl.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 9:06
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalRepository hospitalRepository;

    @Resource
    private DictFeignClient dictFeignClient;

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

    /**
     * 分页查询医院列表
     * @param page 当前页
     * @param limit 每页条数
     * @param hospitalQueryVo hospitalQueryVo
     * @return 分页查询结果
     */
    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        //pageable
        Pageable pageable = PageRequest.of(page - 1, limit);
        //条件匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        //hospitalSetQueryVo -> hospital
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);
        Example<Hospital> example = Example.of(hospital, matcher);
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);
        //医院等级封装
        pages.getContent().forEach(this::setHospitalHosType);
        return pages;
    }

    /**
     * 更新医院的上线状态
     * @param id id
     * @param status 上线状态
     */
    @Override
    public void updateStatus(String id, Integer status) {
        Hospital hospital = hospitalRepository.findById(id).get();
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        hospitalRepository.save(hospital);
    }

    /**
     * 根据id查询医院详细信息
     * @param id id
     * @return 医院信息
     */
    @Override
    public Map<String, Object> getHospById(String id) {
        Hospital hospital = this.setHospitalHosType(hospitalRepository.findById(id).get());
        Map<String, Object> result = new HashMap<>();
        result.put("hospital", hospital);
        result.put("bookingRule", hospital.getBookingRule());
        return result;
    }

    /**
     * 根据医院编号获取医院名称
     * @param hoscode 医院编号
     * @return 医院名称
     */
    @Override
    public String getHospName(String hoscode) {
        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
        if (hospital != null) {
            return hospital.getHosname();
        }
        return null;
    }

    /**
     * 根据医院名称查询
     * @param hosname 医院名称
     * @return 医院列表
     */
    @Override
    public List<Hospital> findByHosname(String hosname) {
        return hospitalRepository.findHospitalByHosnameLike(hosname);
    }

    /**
     * 根据医院编号获取医院挂号详情
     * @param hoscode 医院编号
     * @return 挂号详情
     */
    @Override
    public Map<String, Object> item(String hoscode) {
        Map<String, Object> result = new HashMap<>();
        Hospital hospital = this.setHospitalHosType(this.getByHoscode(hoscode));
        result.put("hospital", hospital);
        result.put("bookingRule", hospital.getBookingRule());
        hospital.setBookingRule(null);
        return result;
    }

    /**
     * 医院等级封装
     * @param hospital Hospital
     */
    private Hospital setHospitalHosType(Hospital hospital) {
        //dictCode和value查
        String hostypeString = dictFeignClient.getName("Hostype", hospital.getHostype());
        //省市区查询
        String province = dictFeignClient.getName(hospital.getProvinceCode());
        String city = dictFeignClient.getName(hospital.getCityCode());
        String district = dictFeignClient.getName(hospital.getDistrictCode());
        hospital.getParam().put("hostypeString", hostypeString);
        hospital.getParam().put("fullAddress", province + city + district);
        return hospital;
    }
}
