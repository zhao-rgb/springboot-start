package com.soft.springmvc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao
 * @className UserVO
 * @Description TODO
 * @Date 2021/12/6
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 账号
     */
    private String username;
}
