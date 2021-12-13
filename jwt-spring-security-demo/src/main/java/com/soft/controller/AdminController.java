package com.soft.controller;

import com.soft.entity.SysAuth;
import com.soft.entity.SysRole;
import com.soft.entity.SysUser;
import com.soft.service.SysUserService;
import com.soft.utils.ResponseUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhao
 * @className AdminController
 * @Description 管理员Controller
 * @Date 2021/12/13
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 查询用户信息
     *
     * @return ResponseUtils
     */
    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/list")
    public ResponseUtils list() {
        List<SysUser> userList = sysUserService.list();
        return ResponseUtils.success(userList);

    }

    /**

     * 查询用户角色
     *
     * @return ResponseUtils
     */
    @PreAuthorize(value = "hasRole('ADMIN') or hasPermission('/user/role', 'sys:role:info')")
    @RequestMapping(value = "/role")
    public ResponseUtils role(Long id) {
        List<SysRole> roleList = sysUserService.findRoleByUserId(id);
        return ResponseUtils.success(roleList);
    }

    /**
     * 查询用户权限
     *
     * @return ResponseUtils
     */
    @PreAuthorize(value = "hasAnyRole('ADMIN', 'USER') and hasPermission('/user/auth', 'sys:auth:info')")
    @RequestMapping(value = "/auth")
    public ResponseUtils auth(Long id) {
        List<SysAuth> authList = sysUserService.findAuthByUserId(id);
        return ResponseUtils.success(authList);
    }

}
