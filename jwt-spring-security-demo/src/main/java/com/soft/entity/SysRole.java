package com.soft.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhao
 * @className SysRole
 * @Description TODO
 * @Date 2021/12/13
 * @Version 1.0
 **/
@Data
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String roleName;
}
