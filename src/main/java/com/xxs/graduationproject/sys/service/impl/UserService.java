package com.xxs.graduationproject.sys.service.impl;

import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.mapper.UserMapper;
import com.xxs.graduationproject.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-08
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

}
