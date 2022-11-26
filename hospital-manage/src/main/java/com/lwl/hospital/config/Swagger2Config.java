package com.lwl.hospital.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger2配置信息
 * @author user-lwl
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                //过滤掉admin路径下的所有页面
//                .paths(Predicates.and(PathSelectors.regex("/P2P/.*")))
                //过滤掉所有error或error.*页面
                //.paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("hospital-API文档")
                .description("hospital")
                .version("1.0")
                .contact(new Contact("user-lwl", "https://github.com/user-lwl", "1399097219@qq.com"))
                .build();
    }


}
