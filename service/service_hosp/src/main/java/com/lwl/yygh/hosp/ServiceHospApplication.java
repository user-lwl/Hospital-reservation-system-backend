package com.lwl.yygh.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author user-lwl
 * @createDate 2022/11/23 16:58
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lwl")
@MapperScan("com.lwl.yygh.hosp.mapper")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
