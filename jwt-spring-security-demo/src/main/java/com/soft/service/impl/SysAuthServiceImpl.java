package com.soft.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.entity.SysAuth;
import com.soft.mapper.SysAuthMapper;
import com.soft.service.SysAuthService;
import org.springframework.stereotype.Service;

/**
 * @author zhao
 * @className SysAuthServiceImpl
 * @Description TODO
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Service
public class SysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuth> implements SysAuthService {
}
