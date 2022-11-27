package com.lwl.yygh.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author user-lwl
 * @createDate 2022/11/23 16:58
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lwl")
@MapperScan("com.lwl.yygh.hosp.mapper")
@CrossOrigin
@EnableFeignClients(basePackages = "com.lwl")
@EnableDiscoveryClient
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
