package com.zqy.blog_admin.system.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        response.setContentType("application/json;charset=utf-8");
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",403);
        map.put("msg","failed");
        map.put("data","权限不足");
        String res = new ObjectMapper().writeValueAsString(map);
        response.getWriter().write(res);
    }
}
