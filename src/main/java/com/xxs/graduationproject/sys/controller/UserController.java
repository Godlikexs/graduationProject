package com.xxs.graduationproject.sys.controller;


import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.mapper.PowerMapper;
import com.xxs.graduationproject.sys.service.IUserService;
import com.xxs.graduationproject.sys.service.impl.PowerService;
import com.xxs.graduationproject.utils.EmailSend;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

    @CrossOrigin//用户名密码 shiro认证登录
    @GetMapping(value = "/loginOut")
    public Result loginOut( User user) {//后端shiro登出
        //调用业务层执行登录业务
        SecurityUtils.getSubject().getSession().removeAttribute("loginUser");
        Result result = new Result();
        result.setCode(200);
        result.setMessage("登出成功");
        return result;
    }

   /* @CrossOrigin*/
    @GetMapping(value = "/getEmail")//获取邮箱验证码
    public Result getEmail( User user, HttpSession httpSession) {// @RequestBody 接收json格式
        //获取验证码
        //判断email是否存在
        Result result = userService.getEmail(user,httpSession);
        return result;
    }

 /*   @CrossOrigin*/
    @GetMapping(value = "/emailLogin")//邮箱登录
    public Result emailLogin( User user,HttpSession httpSession) {// @RequestBody 接收json格式

        //获取验证码
        Result result = userService.emailLogin(user,httpSession);
        return result;
    }

    /*@PostMapping(value = "/getPhone")//测试redis 实现登录次数限制 已废弃
    public Result getPhone( @RequestBody User user) {
        //调用业务层执行登录业务
        Result result = userService.phoneLogin(user);
        return result;
    }*/
    @CrossOrigin
    @GetMapping(value = "/getPhone")
    public Result getPhone( User user,HttpSession httpSession) {
        //调用业务层执行登录业务
        Result result = userService.getPhone(user,httpSession);
        return result;
    }
    @CrossOrigin
    @GetMapping(value = "/phoneLogin")
    public Result phoneLogin( User user,HttpSession httpSession) {
        //调用业务层执行登录业务
        Result result = userService.getPhone(user,httpSession);
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user){//接收前端请求参数能多不能少
        //接收前端参数 调用方法
        Result register = userService.register(user);
        return register;
    }


}

