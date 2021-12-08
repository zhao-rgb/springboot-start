package com.soft.blogapi.service.impl;

import com.soft.blogapi.entity.User;
import com.soft.blogapi.mapper.UserMapper;
import com.soft.blogapi.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxl
 * @since 2021-12-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
