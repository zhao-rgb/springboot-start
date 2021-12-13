package com.soft.controller;

import com.soft.entity.SysUser;
import com.soft.security.entity.SysUserDetails;
import com.soft.service.SysUserService;
import com.soft.utils.ResponseUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhao
 * @className UserController
 * @Description 普通用户Controller
 * @Date 2021/12/13
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private SysUserService sysUserService;

    /**
     * 查询用户信息
     * @return
     */
    @PreAuthorize(value = "hasPermission('/user/info', 'sys:user:info')")
    @RequestMapping(value = "/info")
    public ResponseUtils info(){
        SysUserDetails sysUserDetails = (SysUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        SysUser sysUser = sysUserService.getById(sysUserDetails.getId());
        return ResponseUtils.success(sysUser);
    }
}
