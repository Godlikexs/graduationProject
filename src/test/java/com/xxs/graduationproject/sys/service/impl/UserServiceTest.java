package com.xxs.graduationproject.sys.service.impl;

import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    void selectAll() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("123456");
        Result login = userService.login(user);
        System.out.println(login);
    }
}