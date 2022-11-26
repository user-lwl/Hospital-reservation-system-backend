package com.lwl.yygh.cmn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author user-lwl
 * @createDate 2022/11/24 19:29
 */
@SpringBootApplication
@MapperScan("com.lwl.yygh.cmn.mapper")
@ComponentScan(basePackages = "com.lwl")
@CrossOrigin
public class ServiceCmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
