package com.zqy.blog_admin;

import com.zqy.blog_admin.system.security.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class BlogAdminApplicationTests {


    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private JwtUtils jwtUtils;

    @Test
    void contextLoads() {
    }
    @Test
    void cryptPassword(){
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }
    @Test
    void parseToken() throws Exception {
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiLpmYblqJ8iLCJpYXQiOjE2NjU3MzE3OTMsImV4cCI6MTY2NTczMzU5M30.L8I1n8zSQT6iU4BijZmF6dwz8yJotBZCf9RaaU4h8pNEpRGcAyfr5TgE2p-gSKa6qs-J4SRy24cfwfqYEDqxzA";
        Claims tokenClaim = jwtUtils.getTokenClaim(token);

        System.out.println(tokenClaim);

    }

}
