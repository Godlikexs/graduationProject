package com.xxs.graduationproject.sys.service.impl;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.utils.TenxunSmsSend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    TenxunSmsSend tenxunSmsSend;
    @Test
    void selectAll() {
        User user = new User();
        user.setPhone("15508233112");
        try {
            Result send = tenxunSmsSend.send(user);
            System.out.println(send);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }


}