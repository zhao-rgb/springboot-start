package com.soft.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhao
 * @className SysUser
 * @Description TODO
 * @Date 2021/12/13
 * @Version 1.0
 **/

@Data
@TableName("sys_user")
public class SysUser implements Serializable {
//    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（0-正常，1-删除，2-禁用）
     */
    private String status;

}
