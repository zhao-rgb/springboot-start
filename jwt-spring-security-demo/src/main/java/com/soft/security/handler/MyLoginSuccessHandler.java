package com.soft.security.handler;

import com.soft.security.entity.SysUserDetails;
import com.soft.security.utils.JwtTokenUtil;
import com.soft.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhao
 * @className MyLoginSuccessHandler
 * @Description 自定义登录成功处理类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        String token = JwtTokenUtil.createAccessToken(sysUserDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        ResponseUtils.responseJson(response,ResponseUtils.response(200,"登录成功",tokenMap));

    }
}
