package com.xxs.graduationproject.sys.controller;


import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.service.IUserService;
import com.xxs.graduationproject.utils.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @Autowired
    private EmailSend emailSend;

    @CrossOrigin//用户名密码 shiro认证登录
    @PostMapping(value = "/login")
    public Result login( @RequestBody User user) {
        //调用业务层执行登录业务
        Result login = userService.login(user);
        return login;
    }

    @CrossOrigin
    @PostMapping(value = "/emailLogin")//邮箱登录
    public Result emailLogin(@RequestBody User user, HttpSession httpSession) {// @RequestBody 接收json格式
        //判断email是否存在
        Result result = new Result();
        //调用工具类执行登录业务 @Autowired
        //生成四位随机数
        String a="";
        for (int i = 0; i < 4; i++) {
            int code = new Random().nextInt(10);
            a+=code;
        }

        boolean send = emailSend.send(user.getEmail(), "小松网", "您的验证码是"+a);
        if(send){//如果为真，发送成功

            System.out.println("发送成功");
            result.setData(a);
            result.setCode(200);
            result.setMessage("邮箱发送成功");
            //放入会话域 设置失效时间
            httpSession.setAttribute("code",a);
            httpSession.setMaxInactiveInterval(60000);
        }else{
            System.out.println("发送失败");
            result.setData(a);
            result.setCode(500);
            result.setMessage("邮箱发送失败");
        }
        return result;
    }
    @PostMapping(value = "/getPhone")//测试redis
    public Result getPhone( @RequestBody User user) {
        //调用业务层执行登录业务
        Result result = userService.phoneLogin(user);
        return result;
    }
}

