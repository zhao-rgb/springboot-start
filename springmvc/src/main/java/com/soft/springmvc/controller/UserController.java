package com.soft.springmvc.controller;

import com.soft.springmvc.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhao
 * @className UserController
 * @Description TODO
 * @Date 2021/12/6
 * @Version 1.0
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 查询用户列表
     * @return
     */
    @GetMapping("")
    public List<UserVO> list() {
        //查询列表
        List<UserVO> result = new ArrayList<>();
        ;
        result.add(UserVO.builder()
                .id(1).username("zxl").build());
        result.add(UserVO.builder()
                .id(2).username("wqy").build());
        return result;
    }

}
