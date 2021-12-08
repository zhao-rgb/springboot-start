package com.soft.blogapi.service.impl;

import com.soft.blogapi.entity.Blog;
import com.soft.blogapi.mapper.BlogMapper;
import com.soft.blogapi.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
