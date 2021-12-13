package com.soft.security.handler;

import com.soft.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhao
 * @className MyLogoutSuccessHandler
 * @Description 自定义登出成功处理类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        ResponseUtils.responseJson(response,ResponseUtils.response(200,"登出成功",null));

    }
}
