package com.xxs.graduationproject.sys.controller;


import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.service.IUserService;
import com.xxs.graduationproject.utils.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-08
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService userService;

    @CrossOrigin//用户名密码 shiro认证登录
    @PostMapping(value = "/login")
    public Result login( @RequestBody User user) {
        //调用业务层执行登录业务
        Result login = userService.login(user);
        return login;
    }

    @CrossOrigin
    @PostMapping(value = "/getEmail")//邮箱登录
    public Result emailLogin(@RequestBody User user, HttpSession httpSession) {// @RequestBody 接收json格式
        //判断email是否存在
        //获取验证码
        Result result = userService.emailLogin(user, httpSession);
        return result;
    }


    @PostMapping(value = "/getPhone")//测试redis 实现登录次数限制 已废弃
    public Result getPhone( @RequestBody User user) {
        //调用业务层执行登录业务
        Result result = userService.phoneLogin(user);
        return result;
    }
}

