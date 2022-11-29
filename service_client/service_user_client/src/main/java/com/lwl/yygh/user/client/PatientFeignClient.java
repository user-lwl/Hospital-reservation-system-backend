package com.lwl.yygh.user.client;

import com.lwl.yygh.model.user.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author user-lwl
 * @createDate 2022/11/29 15:44
 */
@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {
    /**
     * 根据就诊人id获取就诊人信息
     * @param id 就诊人id
     * @return 就诊人信息
     */
    @GetMapping("/api/user/patient/inner/get/{id}")
    Patient getPatientOrder(@PathVariable("id") Long id);
}
