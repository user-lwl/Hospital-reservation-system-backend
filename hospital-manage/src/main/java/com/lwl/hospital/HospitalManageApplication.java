package com.lwl.hospital;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = "com.lwl")
@MapperScan("com.lwl.hospital.mapper")
@CrossOrigin
public class HospitalManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManageApplication.class, args);
	}

}
