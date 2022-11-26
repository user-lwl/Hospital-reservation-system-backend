package com.lwl.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.data.domain.Page;
import com.lwl.yygh.hosp.repository.DepartmentRepository;
import com.lwl.yygh.hosp.service.DepartmentService;
import com.lwl.yygh.model.hosp.Department;
import com.lwl.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 14:24
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentRepository departmentRepository;

    /**
     * 添加科室信息
     * @param paramMap 参数
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        Department department = JSON.parseObject(JSON.toJSONString(paramMap), Department.class);
        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());
        if (departmentExist == null) {
            department.setCreateTime(new Date());
        }
        department.setUpdateTime(new Date());
        department.setIsDeleted(0);
        departmentRepository.save(department);
    }

    /**
     * 查询科室接口
     * @param page 页数
     * @param limit 每页信息数
     * @param departmentQueryVo departmentVO
     * @return 分页department
     */
    @Override
    public Page<Department> findPageDepartment(Integer page, Integer limit, DepartmentQueryVo departmentQueryVo) {
        //Pageable
        Pageable pageable = PageRequest.of(page - 1, limit);
        //Example
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                        .withIgnoreCase(true);
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);
        Example<Department> example = Example.of(department, matcher);
        return departmentRepository.findAll(example, pageable);
    }

    /**
     * 删除科室
     * @param hoscode 医院编号
     * @param depcode 科室编号
     */
    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department != null) {
            departmentRepository.deleteById(department.getId());
        }
    }
}
