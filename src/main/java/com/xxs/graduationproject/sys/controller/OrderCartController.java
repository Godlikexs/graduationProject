package com.xxs.graduationproject.sys.controller;


import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.OrderCart;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.service.impl.OrderCartService;
import com.xxs.graduationproject.sys.service.impl.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @CrossOrigin//查询当前购物车列表
    @GetMapping(value = "/cartList")//获取用户购物车未下单商品数量
    public Result cartList(User user){//接收前端请求返回当前用户信息
        //通过用户名查询用户id
        System.err.println("user:"+user);
        //通过用户名查询用户id
        int i = userService.selectIdByUserName(user);
        Result result = orderCartService.selectCartAndProductById(i);
        return result;
    }

    @CrossOrigin//查询当前购物车列表
    @GetMapping(value = "/cartListByPage")//获取用户购物车未下单商品数量
    public Result cartListByPage(OrderCart orderCart){//接收前端请求返回当前用户信息
        //通过用户名查询用户id
        System.err.println("orderCart:"+orderCart);
        //获取shiro主题，可以理解为当前系统用户  调用自定义Realms中认证方法
        Subject subject = SecurityUtils.getSubject();
        User loginUser = (User) subject.getSession().getAttribute("loginUser");
        //通过用户名查询用户id

        Result result = orderCartService.selectCartListByPage(orderCart, loginUser.getId());
        return result;
    }


}

