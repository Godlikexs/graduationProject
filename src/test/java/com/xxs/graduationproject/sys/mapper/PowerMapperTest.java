package com.xxs.graduationproject.sys.mapper;

import com.xxs.graduationproject.sys.entity.Power;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PowerMapperTest {
    @Autowired
    private PowerMapper powerMapper;
    @Test
    void queryOneByRoleName() {
        List<Power> 超级管理员 = powerMapper.queryOneByRoleName("超级管理员");
    }
}