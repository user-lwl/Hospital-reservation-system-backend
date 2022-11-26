package com.lwl.yygh.hosp.controller.api;

import com.lwl.yygh.common.exception.YyghException;
import com.lwl.yygh.common.helper.HttpRequestHelper;
import com.lwl.yygh.common.result.Result;
import com.lwl.yygh.common.result.ResultCodeEnum;
import com.lwl.yygh.common.utils.MD5;
import com.lwl.yygh.hosp.service.HospitalService;
import com.lwl.yygh.hosp.service.HospitalSetService;
import com.lwl.yygh.model.hosp.Hospital;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author user-lwl
 * @createDate 2022/11/26 9:07
 */
@RequestMapping("/api/hosp")
@RestController
public class ApiController {
    @Resource
    private HospitalService hospitalService;

    @Resource
    private HospitalSetService hospitalSetService;

    /**
     * 上传医院接口
     * @param request 请求
     * @return 是否成功
     */
    @PostMapping("/saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        //获取医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //签名加密
        String hospSign = (String) paramMap.get("sign");
        //比对签名
        //查库
        String hoscode = (String) paramMap.get("hoscode");
        String singKey = hospitalSetService.getSignKey(hoscode);
        //加密
        String signKeyMd5 = MD5.encrypt(singKey);
        if (!hospSign.equals(signKeyMd5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        //"+"->" "
        String logoData = (String) paramMap.get("logData");
        logoData = logoData.replaceAll(" ", "+");
        paramMap.put("logoData", logoData);
        hospitalService.save(paramMap);
        return Result.ok();
    }

    /**
     * 查询医院接口
     * @param request 请求
     * @return 医院信息
     */
    @PostMapping("/hospital/show")
    public Result<Hospital> getHospital(HttpServletRequest request) {
        //获取医院信息
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
        //根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }
}
