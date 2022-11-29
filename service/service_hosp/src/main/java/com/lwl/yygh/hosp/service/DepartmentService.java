package com.lwl.yygh.hosp.service;

import com.lwl.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;
import com.lwl.yygh.model.hosp.Department;
import com.lwl.yygh.vo.hosp.DepartmentQueryVo;

import java.util.List;
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

    /**
     * 查询医院所有科室的列表
     * @param hoscode 医院编号
     * @return 科室列表
     */
    List<DepartmentVo> findDeptTree(String hoscode);

    /**
     * 根据科室编号和医院编号查询科室名称
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @return 科室名称
     */
    String getDepName(String hoscode, String depcode);

    /**
     * 根据可是编号和医院编号查询科室
     * @param hoscode 医院编号
     * @param depcode 科室编号
     * @return 科室信息
     */
    Department getDepartment(String hoscode, String depcode);
}
