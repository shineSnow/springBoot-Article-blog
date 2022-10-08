package com.zqy.blog_admin.system.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30) // v2 不同
                .apiInfo(
                        new ApiInfoBuilder()
                        .contact(new Contact("zqy","","12312.@qq.com"))
                        .title("swagger3博客项目")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zqy.blog_admin.system.controller")) // 设置扫描路径

                .build();
    }
}
