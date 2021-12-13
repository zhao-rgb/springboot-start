package com.soft.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhao
 * @className SysRoleAuth
 * @Description TODO
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Data
@TableName("sys_role_auth")
public class SysRoleAuth implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long roleId;

    private Long authId;
}
