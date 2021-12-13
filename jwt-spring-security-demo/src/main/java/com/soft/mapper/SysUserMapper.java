package com.soft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.entity.SysAuth;
import com.soft.entity.SysRole;
import com.soft.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhao
 * @className SysUserMapper
 * @Description 系统用户Mapper
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
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
