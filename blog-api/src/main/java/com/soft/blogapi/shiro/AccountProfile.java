package com.soft.blogapi.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhao
 * @className AccountProfile
 * @Description 登陆账号类
 * @Date 2021/12/8
 * @Version 1.0
 **/
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;
}
