package com.soft.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.entity.SysAuth;
import com.soft.entity.SysRole;
import com.soft.entity.SysUser;
import com.soft.mapper.SysUserMapper;
import com.soft.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhao
 * @className SysUserServiceImpl
 * @Description TODO
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return
     */
    @Override
    public SysUser findUserByUserName(String username) {
        return this.baseMapper.selectOne(
                new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername,username).ne(SysUser::getStatus,"1"));
    }

    /**
     * 根据用户ID查询角色
     * @param userId 用户id
     * @return
     */
    @Override
    public List<SysRole> findRoleByUserId(Long userId) {
        return this.baseMapper.findRoleByUserId(userId);
    }

    /**
     * 根据用户ID查询权限
     * @param userId 用户id
     * @return
     */
    @Override
    public List<SysAuth> findAuthByUserId(Long userId) {
        return this.baseMapper.findAuthByUserId(userId);
    }
}
