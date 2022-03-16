package com.xxs.graduationproject.sys.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderCartMapperTest {
    @Autowired
    OrderCartMapper orderCartMapper;

    @Test
    void selectNumberByUserIdAndState() {
        int i = orderCartMapper.selectNumberByUserIdAndState(1);
        System.out.println(i);
    }
}