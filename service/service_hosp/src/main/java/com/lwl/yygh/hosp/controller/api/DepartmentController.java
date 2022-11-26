package com.lwl.yygh.hosp.controller.api;

import org.springframework.data.domain.Page;
import com.lwl.yygh.common.exception.YyghException;
import com.lwl.yygh.common.helper.HttpRequestHelper;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.common.result.ResultCodeEnum;
import com.lwl.yygh.common.utils.MD5;
import com.lwl.yygh.hosp.service.DepartmentService;
import com.lwl.yygh.hosp.service.HospitalSetService;
import com.lwl.yygh.model.hosp.Department;
import com.lwl.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 14:25
 */
@RequestMapping("/api/hosp")
@RestController
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @Resource
    private HospitalSetService hospitalSetService;

    /**
     * 上传科室接口
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        //传递科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //MD5加密,判断签名
        String hospSign = (String) paramMap.get("sign");
        String signKey = hospitalSetService.getSignKey(hoscode);
        String signKeyMD5 = MD5.encrypt(signKey);
        if (!hospSign.equals(signKeyMD5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        //保存
        departmentService.save(paramMap);
        return Result.ok();
    }

    /**
     * 查询科室接口
     * @param request 请求
     * @return 科室信息
     */
    @PostMapping("/department/list")
    public Result<Page<Department>> findDepartment(HttpServletRequest request) {
        //传递科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //当前页
        Integer page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : (Integer) paramMap.get("page");
        Integer limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : (Integer) paramMap.get("limit");
        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        Page<Department> pageModel = departmentService.findPageDepartment(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 删除科室接口
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        //传递科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        departmentService.remove(hoscode, depcode);
        return Result.ok();
    }
}
