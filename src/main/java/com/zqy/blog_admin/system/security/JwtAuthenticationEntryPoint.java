package com.zqy.blog_admin.system.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        System.out.println("无凭证"+e);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",401);
        map.put("msg","401 你还没有登录");
        String json = JSON.toJSONString(map);
        response.setStatus(200);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
