package com.soft.blogapi.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhao
 * @className LoginDto
 * @Description 登陆Dto类
 * @Date 2021/12/8
 * @Version 1.0
 **/
@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
