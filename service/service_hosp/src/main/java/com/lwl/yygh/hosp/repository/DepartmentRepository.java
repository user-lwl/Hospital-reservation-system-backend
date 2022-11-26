package com.lwl.yygh.hosp.repository;

import com.lwl.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author user-lwl
 * @createDate 2022/11/26 14:22
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    /**
     * 查询科室
     * @param hoscode 医院编码
     * @param depcode 科室编码
     * @return 科室信息
     */
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
