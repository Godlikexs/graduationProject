package com.xxs.graduationproject.sys.mapper;

import com.xxs.graduationproject.sys.entity.OrderCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-15
 */
public interface OrderCartMapper extends BaseMapper<OrderCart> {
    int selectNumberByUserIdAndState(Integer id);//查询用户下状态为0尚未下单的数量

    List<OrderCart> selectCartAndProductById(Integer id);
}
