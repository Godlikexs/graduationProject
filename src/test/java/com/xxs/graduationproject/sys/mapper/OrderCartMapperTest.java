package com.xxs.graduationproject.sys.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        Page<OrderCart> objectPage = new Page<>(1, 1);
        Page<OrderCart> orderCartPage = orderCartMapper.selectCartByPage(objectPage,1);
        List<OrderCart> records = orderCartPage.getRecords();
        for (OrderCart record : records) {
            System.out.println(record);
        }
        System.out.println("============================");
        orderCartPage .hasNext();
        System.out.println("是否有下一页:"+orderCartPage .hasNext());
        System.out.println("当前页:"+orderCartPage .getCurrent());
        System.out.println("总数："+orderCartPage .getTotal());
        System.out.println("getPages():"+orderCartPage .getPages());
        System.out.println("getOrders():"+orderCartPage .getOrders());
        System.out.println("getSize():"+orderCartPage .getSize());
    }

    @Test
    void selectNumberByPage() {
        List<OrderCart> orderCarts = orderCartMapper.selectCartAndProductById(1);
    }
}