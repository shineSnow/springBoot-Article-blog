package com.zqy.blog_admin.system.security;

import com.zqy.blog_admin.system.dto.JwtProperties;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends BasicAuthenticationFilter {

    private UserDetailsService myUserDetailsService;

    @Resource
    private JwtUtils jwtTokenUtil;

    @Resource
    private JwtProperties jwtProperties;


    public JwtTokenFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils, JwtProperties jwtProperties, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtTokenUtil = jwtUtils;
        this.jwtProperties = jwtProperties;
        this.myUserDetailsService = userDetailsService;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasLength(token)) {
            String username = null;
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            } catch (ExpiredJwtException e) {
               response.setContentType("application/json;charset=utf8");
               response.getWriter().write("token?????????");
               return;
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                System.out.println("this.userDetailsService:"+myUserDetailsService);

                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(token,userDetails)){
                    // ????????????????????? authentication?????????????????????
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // ??? authentication ?????? ThreadLocal?????????????????????????????????
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
