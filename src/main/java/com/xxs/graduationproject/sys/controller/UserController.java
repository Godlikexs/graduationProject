package com.xxs.graduationproject.sys.controller;


import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @CrossOrigin
    @PostMapping(value = "/login")
    public Result login( @RequestBody User user) {
        //调用业务层执行登录业务
        Result login = userService.login(user);
        return login;
    }

}

