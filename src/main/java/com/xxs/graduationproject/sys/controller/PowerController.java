package com.xxs.graduationproject.sys.controller;


import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.service.impl.PowerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/api")
public class PowerController {
    @Autowired
    private PowerService powerService;

    @CrossOrigin
    @GetMapping(value = "/menu")
    public Result menu(User user){//接收前端请求参数能多不能少
        //获取shiro主题，可以理解为当前系统用户  调用自定义Realms中认证方法
        Subject subject = SecurityUtils.getSubject();
        User loginUser = (User) subject.getSession().getAttribute("loginUser");
        System.out.println(loginUser);
        Result result = powerService.selectPowerByName(loginUser);
        return result;
    }

    @CrossOrigin
    @GetMapping(value = "/userInfo")
    public Result userInfo(){//接收前端请求返回当前用户信息
        //获取shiro主题，可以理解为当前系统用户  调用自定义Realms中认证方法
        Subject subject = SecurityUtils.getSubject();
        User loginUser = (User) subject.getSession().getAttribute("loginUser");
        Result result = new Result();
        result.setCode(200);
        result.setData(loginUser);
        System.err.println(loginUser);
        return result;
    }

}

