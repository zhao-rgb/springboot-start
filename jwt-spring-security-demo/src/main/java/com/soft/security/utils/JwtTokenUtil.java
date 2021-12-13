package com.soft.security.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.soft.security.config.JwtConfig;
import com.soft.security.entity.SysUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * @author zhao
 * @className JwtTokenUtil
 * @Description JWT生产Token工具类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Slf4j
public class JwtTokenUtil {
    /**
     * 创建token
     * @param sysUserDetails 用户信息
     * @return String
     */
    public static String createAccessToken(SysUserDetails sysUserDetails){
        //设置JWT
        String token = Jwts.builder()
                //用户id
                .setId(sysUserDetails.getId().toString())
                //主题
                .setSubject(sysUserDetails.getUsername())
                //签发时间
                .setIssuedAt(new Date())
                //签发者
                .setIssuer("zxl")
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.expiration))
                //签名算法、密钥
                .signWith(SignatureAlgorithm.HS512,JwtConfig.secret)
                //自定义其他属性，如用户组织机构ID，用户所拥有的角色，用户权限信息等
                .claim("authorities", JSON.toJSONString(sysUserDetails.getAuthorities())).compact();
        return JwtConfig.tokenPrefix + token;
    }

    /**
     * 解析token
     * @param token token信息
     * @return  SysUserDetails系统用户详情
     */
    public static SysUserDetails parseAccessToken(String token) {
        SysUserDetails sysUserDetails = null;
        if (StringUtils.isNotEmpty(token)) {
            try {
                //去除JWT前缀
                token = token.substring(JwtConfig.tokenPrefix.length());
                //解析token
                Claims claims = Jwts.parser().setSigningKey(JwtConfig.secret).parseClaimsJws(token).getBody();
                //获取用户信息
                sysUserDetails = new SysUserDetails();
                sysUserDetails.setId(Long.valueOf(claims.getId()));
                sysUserDetails.setUsername(claims.getSubject());
                //获取角色
                Set<GrantedAuthority> authorities = new HashSet<>();
                String authority  = claims.get("authorities").toString();
                if (StringUtils.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority,new TypeReference<>(){

                    });
                    for (Map<String,String> role : authorityList) {
                        if (!role.isEmpty()) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                sysUserDetails.setAuthorities(authorities);
            } catch (Exception e){
                log.error("解析Token异常：" + e);
            }
        }
        return sysUserDetails;
    }
}
