package com.xxs.graduationproject.sys.service;

import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.OrderCart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-15
 */
public interface IOrderCartService extends IService<OrderCart> {

    Result selectNumberByUserIdAndState(Integer id);//查询用户下状态为0尚未下单的数量

    Result selectCartAndProductById(Integer id);

    Result selectCartListByPage(OrderCart orderCar,Integer id);//使用orderCart封装page
}
