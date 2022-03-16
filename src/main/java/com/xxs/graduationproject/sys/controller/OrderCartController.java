package com.xxs.graduationproject.sys.controller;


import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.service.impl.OrderCartService;
import com.xxs.graduationproject.sys.service.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-15
 */
@RestController
@RequestMapping("/api")
public class OrderCartController {
    @Resource
    private UserService userService;
    @Resource
    private OrderCartService orderCartService;
    @CrossOrigin
    @GetMapping(value = "/cartInfo")//获取用户购物车未下单商品数量
    public Result cartInfo(User user){//接收前端请求返回当前用户信息
        //通过用户名查询用户id
        int i = userService.selectIdByUserName(user);
        Result result = orderCartService.selectNumberByUserIdAndState(i);
        return result;
    }
}

