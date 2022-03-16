package com.xxs.graduationproject.sys.service.impl;

import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.OrderCart;
import com.xxs.graduationproject.sys.mapper.OrderCartMapper;
import com.xxs.graduationproject.sys.service.IOrderCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-15
 */
@Service
public class OrderCartService extends ServiceImpl<OrderCartMapper, OrderCart> implements IOrderCartService {
    @Resource
    private OrderCartMapper orderCartMapper;

    @Resource
    private Result result;
    @Override
    public Result selectNumberByUserIdAndState(Integer id) {
        int i = orderCartMapper.selectNumberByUserIdAndState(id);
        if (i>0){
            result.setMessage("查询成功");
            result.setCode(200);
            result.setData(i);
        }
        return result;
    }

    @Override
    public Result selectCartAndProductById(Integer id) {
        List<OrderCart> orderCarts = orderCartMapper.selectCartAndProductById(id);
        if (orderCarts != null){
            result.setMessage("查询成功");
            result.setCode(200);
            result.setData(orderCarts);
        }
        return result;
    }
}
