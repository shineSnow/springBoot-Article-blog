package com.zqy.blog_admin.system.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zqy.blog_admin.system.entity.User;
import com.zqy.blog_admin.system.response.AjaxResult;
import com.zqy.blog_admin.system.service.UserService;
import com.zqy.blog_admin.system.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private UserService userService;

    private AuthenticationManager authenticationManager;


    public AuthenticationFilter(AuthenticationManager authenticationManager,JwtUtils jwtUtils){
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        super.setFilterProcessesUrl("/login");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

//        try {
//            User loginUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
//            System.out.println("authenticate" + authenticationToken);
////            setDetails(request, authenticationToken);
//            return getAuthenticationManager().authenticate(authenticationToken);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return getAuthenticationManager().authenticate(null);
//        }

        // 从输入流中获取到登录的信息
        try {
            User loginUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        SecurityUser jwtUser = (SecurityUser) authResult.getPrincipal();
        System.out.println("successfulAuthentication");

        String token = jwtUtils.createToken(jwtUser.getUsername());
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("token",token);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",token);
        map.put("code",200);
        map.put("msg","ok");
        response.getWriter().write(JSON.toJSONString(map));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("unsuccessfulAuthentication");
        System.out.println(failed);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        if(failed instanceof BadCredentialsException){
            response.getWriter().write(JSON.toJSONString(AjaxResult.error(10003,"用户名或密码错误")));
        } else {
            response.getWriter().write(JSON.toJSONString(AjaxResult.error(failed.getMessage())));
        }
    }



}
