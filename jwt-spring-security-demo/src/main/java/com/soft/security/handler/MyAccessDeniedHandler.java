package com.soft.security.handler;

import com.soft.utils.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhao
 * @className MyAccessDeniedHandler
 * @Description 自定义无权限处理类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.responseJson(response,ResponseUtils.response(403,"拒绝访问",accessDeniedException.getMessage()));
    }
}
