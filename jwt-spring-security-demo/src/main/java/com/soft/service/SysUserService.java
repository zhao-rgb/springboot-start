package com.soft.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft.entity.SysAuth;
import com.soft.entity.SysRole;
import com.soft.entity.SysUser;

import java.util.List;

/**
 * @author zhao
 * @className SysUserService
 * @Description TODO
 * @Date 2021/12/13
 * @Version 1.0
 **/
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return
     */
    SysUser findUserByUserName(String username);

    /**
     * 根据用户ID查询角色
     * @param userId 用户id
     * @return
     */
    List<SysRole> findRoleByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     * @param userId 用户id
     * @return
     */
    List<SysAuth> findAuthByUserId(Long userId);
}
