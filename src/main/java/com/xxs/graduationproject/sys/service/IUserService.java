package com.xxs.graduationproject.sys.service;

import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XiongXiaoSong
 * @since 2022-03-08
 */
public interface IUserService extends IService<User> {

    public Result login(User user);

    public  Result phoneLogin(User user);

    public Result emailLogin(User user, HttpSession httpSession);
}
