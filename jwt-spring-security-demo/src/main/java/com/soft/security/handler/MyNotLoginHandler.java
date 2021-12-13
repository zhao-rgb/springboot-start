package com.soft.security.handler;

import com.soft.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhao
 * @className MyNotLoginHandler
 * @Description 自定义未登录处理类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Component
public class MyNotLoginHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils.responseJson(response,ResponseUtils.response(401,"未登录",authException.getMessage()));
    }
}
