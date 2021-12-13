package com.soft.security.handler;

import com.soft.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhao
 * @className MyLoginFailureHandler
 * @Description 自定义登录失败处理类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Component
public class MyLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ResponseUtils.responseJson(response,ResponseUtils.response(500,"登录失败",exception.getMessage()));
    }
}
