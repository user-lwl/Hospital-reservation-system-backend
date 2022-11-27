package com.lwl.yygh.hosp.service;

import com.lwl.yygh.model.hosp.Hospital;
import com.lwl.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
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

    /**
     * 分页查询医院列表
     * @param page 当前页
     * @param limit 每页条数
     * @param hospitalQueryVo hospitalQueryVo
     * @return 分页查询结果
     */
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 更新医院的上线状态
     * @param id id
     * @param status 上线状态
     */
    void updateStatus(String id, Integer status);

    /**
     * 根据id查询医院详细信息
     * @param id id
     * @return 医院信息
     */
    Map<String, Object> getHospById(String id);

    /**
     * 根据医院编号获取医院名称
     * @param hoscode 医院编号
     * @return 医院名称
     */
    String getHospName(String hoscode);

    /**
     * 根据医院名称查询
     * @param hosname 医院名称
     * @return 医院列表
     */
    List<Hospital> findByHosname(String hosname);

    /**
     * 根据医院编号获取医院挂号详情
     * @param hoscode 医院编号
     * @return 挂号详情
     */
    Map<String, Object> item(String hoscode);
}
