package com.xxs.graduationproject.test;

import com.xxs.graduationproject.GraduationProjectApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraduationProjectApplication.class)
public class RedisTest {
    @Autowired
    RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
    @Test
    public  void test02(){
      redisTemplate.opsForValue().set("name","xxs",1, TimeUnit.MINUTES);
    }
    @Test
    public  void test03(){


    }
}
