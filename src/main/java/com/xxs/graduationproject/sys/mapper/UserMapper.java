package com.xxs.graduationproject.sys.mapper;

import com.xxs.graduationproject.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //多表查询 通过用户名查询角色信息
    User queryOneByUserName(String userName);

}
