package com.lwl.yygh.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author user-lwl
 * @createDate 2022/11/28 9:25
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.lwl")
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lwl.yygh.user.mapper")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
