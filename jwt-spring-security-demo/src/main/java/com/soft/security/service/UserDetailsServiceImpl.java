package com.soft.security.service;


import com.soft.entity.SysRole;
import com.soft.entity.SysUser;
import com.soft.security.entity.SysUserDetails;
import com.soft.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhao
 * @className UserDetailsServiceImpl
 * @Description 用户登录Service实现
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;

    /**
     * 根据用户名查用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findUserByUserName(username);
        if (sysUser != null){
            SysUserDetails sysUserDetails = new SysUserDetails();
            BeanUtils.copyProperties(sysUser,sysUserDetails);
            //角色集合
            Set<GrantedAuthority> authorities = new HashSet<>();

            List<SysRole> roleList = sysUserService.findRoleByUserId(sysUserDetails.getId());
            roleList.forEach(role ->{
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });

            sysUserDetails.setAuthorities(authorities);
            return sysUserDetails;
        }
        return null;
    }
}
