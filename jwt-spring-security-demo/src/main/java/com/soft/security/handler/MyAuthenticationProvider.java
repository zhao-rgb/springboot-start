package com.soft.security.handler;

import com.soft.security.entity.SysUserDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhao
 * @className MyAuthenticationProvider
 * @Description 自定义用户登录验证处理类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 身份验证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取用户名
        String username = (String) authentication.getPrincipal();
        //获取密码
        String password = (String) authentication.getCredentials();

        SysUserDetails sysUserDetails = (SysUserDetails) userDetailsService.loadUserByUsername(username);
        if (sysUserDetails == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        if (!new BCryptPasswordEncoder().matches(password,sysUserDetails.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        if ("2".equals(sysUserDetails.getStatus())){
            throw new LockedException("用户已禁用");
        }
        return new UsernamePasswordAuthenticationToken(sysUserDetails,password,sysUserDetails.getAuthorities());
    }

    /**
     * 支持指定的身份验证
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
