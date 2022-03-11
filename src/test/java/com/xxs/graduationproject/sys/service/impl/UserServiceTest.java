package com.xxs.graduationproject.sys.service.impl;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.utils.TenxunSmsSend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    TenxunSmsSend tenxunSmsSend;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Test
    void selectAll() {

        SetOperations<String, Object> set = redisTemplate.opsForSet();
     set.add("islogin",5);

        Boolean islogin = set.isMember("islogin", 5);
        System.out.println(islogin
        );

    }


}