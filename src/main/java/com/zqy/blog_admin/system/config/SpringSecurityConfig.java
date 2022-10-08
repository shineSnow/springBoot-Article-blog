package com.zqy.blog_admin.system.config;


import com.zqy.blog_admin.system.dto.JwtProperties;
import com.zqy.blog_admin.system.security.*;
import com.zqy.blog_admin.system.serviceImpl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public UserDetailsService myUserDetailService(){
//        return new UserDetailsServiceImpl();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailService()).passwordEncoder(passwordEncoder());
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager()throws Exception{
        return super.authenticationManager();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
              .csrf().disable()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .authorizeRequests()
              .antMatchers("/doc.html").permitAll()
              .antMatchers("/login").permitAll()
              //登录URL
              .antMatchers("/swagger**/**").permitAll()
              .antMatchers("/webjars/**").permitAll()
              .antMatchers("/v2/**").permitAll()
              .antMatchers("/v3/**").permitAll()

              .anyRequest()
              .authenticated()
              .and()

              .exceptionHandling()
              .authenticationEntryPoint(jwtAuthenticationEntryPoint)
              .accessDeniedHandler(jwtAccessDeniedHandler)

              .and()
              .addFilterAt(new JwtTokenFilter(authenticationManager(),jwtUtils,jwtProperties,userDetailsService), BasicAuthenticationFilter.class)
              .addFilterAt(new AuthenticationFilter(authenticationManager(),jwtUtils),UsernamePasswordAuthenticationFilter.class)
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
