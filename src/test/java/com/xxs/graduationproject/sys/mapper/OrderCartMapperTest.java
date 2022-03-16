package com.xxs.graduationproject.sys.mapper;

import com.xxs.graduationproject.sys.entity.OrderCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderCartMapperTest {
    @Autowired
    OrderCartMapper orderCartMapper;

    @Test
    void selectNumberByUserIdAndState() {
        List<OrderCart> orderCarts = orderCartMapper.selectCartAndProductById(1);
        for (OrderCart orderCart : orderCarts) {
            System.out.println(orderCart);
        }
    }
}