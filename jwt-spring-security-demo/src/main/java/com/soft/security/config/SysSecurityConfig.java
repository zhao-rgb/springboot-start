package com.soft.security.config;

import com.soft.security.filter.JwtAuthenticationFilter;
import com.soft.security.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.annotation.Resource;

/**
 * @author zhao
 * @className SysSecurityConfig
 * @Description 系统安全核心配置: 顶部注解开启允许安全配置、开启方法权限注解
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SysSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 无权限处理类
     */
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 用户未登录处理类
     */
    @Resource
    private MyNotLoginHandler myNotLoginHandler;

    /**
     * 用户登录成功处理类
     */
    @Resource
    private MyLoginSuccessHandler myLoginSuccessHandler;

    /**
     * 用户登录失败处理类
     */
    @Resource
    private MyLoginFailureHandler myLoginFailureHandler;

    /**
     * 用户登出成功处理类
     */
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    /**
     * 用户登录验证
     */
    @Resource
    private MyAuthenticationProvider myAuthenticationProvider;

    /**
     * 用户权限注解
     */
    @Resource
    private MyPermissionEvaluator myPermissionEvaluator;

    /**
     * 加密方式
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义PermissionEvaluator
     *
     * @return DefaultWebSecurityExpressionHandler
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(myPermissionEvaluator);
        return handler;
    }

    /**
     * 用户登录验证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 权限配置
        http.authorizeRequests()
                // 获取白名单（不进行权限验证）
                .antMatchers(JwtConfig.antMatchers.split(",")).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                // 配置未登录处理类
                .and().httpBasic().authenticationEntryPoint(myNotLoginHandler)
                // 配置登录URL
                .and().formLogin().loginProcessingUrl("/login/submit")
                // 配置登录成功处理类
                .successHandler(myLoginSuccessHandler)
                // 配置登录失败处理类
                .failureHandler(myLoginFailureHandler)
                // 配置登出地址
                .and().logout().logoutUrl("/logout/submit")
                // 配置用户登出处理类
                .logoutSuccessHandler(myLogoutSuccessHandler)
                // 配置没有权限处理类
                .and().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                // 开启跨域
                .and().cors()
                // 禁用跨站请求伪造防护
                .and().csrf().disable();
        // 禁用session（使用Token认证）
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT过滤器
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }
}
