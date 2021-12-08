package com.soft.blogapi.util;

import com.soft.blogapi.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author zhao
 * @className ShiroUtil工具类
 * @Description TODO
 * @Date 2021/12/8
 * @Version 1.0
 **/
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
