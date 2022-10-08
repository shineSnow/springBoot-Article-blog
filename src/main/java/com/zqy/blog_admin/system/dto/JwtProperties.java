package com.zqy.blog_admin.system.dto;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.jwt")
@Data
public class JwtProperties{

    /*秘钥*/

    private String secret;

    /*
    * 过期时间
    * */
    private Integer expire;

    /*
    * 名称
    * */
    private String header;
}
