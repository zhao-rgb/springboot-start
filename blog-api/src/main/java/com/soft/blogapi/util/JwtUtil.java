package com.soft.blogapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhao
 * @className JwtUtil
 * @Description JwtUtil工具类
 * @Date 2021/12/8
 * @Version 1.0
 **/
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "zxl.jwt")
public class JwtUtil {
    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     * @param userId
     * @return
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public Claims getClaimByToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期 true过期
     * @param expiration
     * @return
     */
    public boolean isTokenExpired(Date expiration){
        return expiration.before(new Date());
    }
}
