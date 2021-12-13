package com.soft.security.handler;

import com.soft.entity.SysAuth;
import com.soft.security.entity.SysUserDetails;
import com.soft.service.SysUserService;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhao
 * @className MyPermissionEvaluator
 * @Description 用户权限注解处理类
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Resource
    private SysUserService sysUserService;

    /**
     * 判断是否拥有权限
     *
     * @param authentication 用户身份
     * @param targetUrl      目标路径
     * @param permission     路径权限
     * @return 是否拥有权限
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        //用户权限
        Set<String> permissions = new HashSet<>();

        List<SysAuth> authList = sysUserService.findAuthByUserId(sysUserDetails.getId());
        authList.forEach(auth->{
            permissions.add(auth.getPermission());
        });

        //判断是否拥有权限
        return permissions.contains(permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}
