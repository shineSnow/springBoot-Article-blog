package com.zqy.blog_admin.system.security;

import com.zqy.blog_admin.system.dto.JwtProperties;
import com.zqy.blog_admin.system.entity.User;
import io.jsonwebtoken.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtUtils {

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 生成token
     * @param subject
     * @return
     */
    public String createToken(String subject){

        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + jwtProperties.getExpire() * 60 * 1000);

        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getSecret())
                .compact();
    }

    public String createToken(User user){
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + jwtProperties.getExpire() * 60 * 1000);
        Map claims = new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("userId",user.getId());
        return Jwts.builder()
                .setClaims(claims)
                .setHeaderParam("type","JWT")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getSecret())
                .compact();
    }

    /**
     * 获取token中注册信息
     * @param token
     * @return
     */
    public Claims getTokenClaim(String token) throws Exception {

        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
           throw new Exception("token解析失败");

        }
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 验证令牌
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        SecurityUser user = (SecurityUser) userDetails;
        String username = getUsernameFromToken(token);

        return (username.equals(user.getUsername()) && !isTokenExpire(token));
    }


    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    public Boolean isTokenExpire(String token){
        try {
            Claims claims = getTokenClaim(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }



    /**
     * 验证token是否过期失效
     * @param expireTime
     * @return
     */
    public Boolean isTokenExpire(Date  expireTime){

        return expireTime.before(new Date());
    }


    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) throws Exception {
        return getTokenClaim(token).getSubject();
    }
    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) throws Exception {
        return getTokenClaim(token).getIssuedAt();
    }



}
