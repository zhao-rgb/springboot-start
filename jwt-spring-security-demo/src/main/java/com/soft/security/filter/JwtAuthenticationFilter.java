package com.soft.security.filter;

import com.soft.security.config.JwtConfig;
import com.soft.security.entity.SysUserDetails;
import com.soft.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhao
 * @className JwtAuthenticationFilter
 * @Description JWT权限过滤器，用于验证Token是否合法
 * @Date 2021/12/13
 * @Version 1.0
 **/
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        //取出token
        String token = request.getHeader(JwtConfig.tokenHeader);

        if(token != null && token.startsWith(JwtConfig.tokenPrefix)) {
            SysUserDetails sysUserDetails = JwtTokenUtil.parseAccessToken(token);

            System.out.println(sysUserDetails);

            if (sysUserDetails != null){
                UsernamePasswordAuthenticationToken authentication  = new UsernamePasswordAuthenticationToken(
                        sysUserDetails,sysUserDetails.getId(),sysUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
