package com.soft.blogapi.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhao
 * @className JwtToken
 * @Description TODO
 * @Date 2021/12/8
 * @Version 1.0
 **/
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
