package com.zqy.blog_admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class BlogAdminApplicationTests {


    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
    }
    @Test
    void cryptPassword(){
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);


    }

}
