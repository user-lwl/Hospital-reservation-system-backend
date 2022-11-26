package com.lwl.yygh.hosp.service;

import org.springframework.data.domain.Page;
import com.lwl.yygh.model.hosp.Department;
import com.lwl.yygh.vo.hosp.DepartmentQueryVo;

import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 14:23
 */
public interface DepartmentService {
    /**
     * 上传科室接口
     * @param paramMap 参数
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询科室接口
     * @param page 页数
     * @param limit 每页信息数
     * @param departmentQueryVo departmentVO
     * @return 分页Department
     */
    Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo);

    /**
     * 删除科室接口
     * @param hoscode 医院编号
     * @param depcode 科室编号
     */
    void remove(String hoscode, String depcode);
}
