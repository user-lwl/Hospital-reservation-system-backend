package com.lwl.yygh.hosp.repository;

import com.lwl.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author user-lwl
 * @createDate 2022/11/26 9:05
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {

    /**
     * 判断是否存在数据
     * @param hoscode 医院编号
     * @return 是否存在
     */
    Hospital getHospitalByHoscode(String hoscode);

    /**
     * 根据医院名称查询
     * @param hosname 医院名称
     * @return 医院列表
     */
    List<Hospital> findHospitalByHosnameLike(String hosname);
}
