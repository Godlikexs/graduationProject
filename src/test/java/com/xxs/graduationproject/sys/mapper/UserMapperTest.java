package com.xxs.graduationproject.sys.mapper;

import com.xxs.graduationproject.sys.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    void queryOneByUserName() {
        List<User> admin = userMapper.queryOneByUserName("admin");
        for (User user : admin) {
            System.out.println(user);
        }
    }
}