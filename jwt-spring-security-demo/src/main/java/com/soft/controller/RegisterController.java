package com.soft.controller;

import com.soft.entity.SysUser;
import com.soft.entity.SysUserRole;
import com.soft.service.SysUserRoleService;
import com.soft.service.SysUserService;
import com.soft.utils.ResponseUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhao
 * @className RegisterController
 * @Description 注册用户实例Controller
 * @Date 2021/12/13
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 注册普通用户
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/user")
    public ResponseUtils user(String username,String password){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(bCryptPasswordEncoder.encode(password));
        sysUser.setStatus("0");
        sysUserService.save(sysUser);

        // 将其设置为普通用户权限
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        sysUserRole.setRoleId(2L);
        sysUserRoleService.save(sysUserRole);

        return ResponseUtils.success(sysUser);
    }
}
