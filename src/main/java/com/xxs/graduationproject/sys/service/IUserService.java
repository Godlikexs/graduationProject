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

    Result login(User user);//用户名密码登录

    Result getPhone(User user,HttpSession httpSession);//获取手机验证码

    Result phoneLogin(User user,HttpSession httpSession);//手机号登录

    Result getEmail(User user,HttpSession httpSession);//获取邮箱验证码

    Result emailLogin(User user,HttpSession httpSession);//邮箱登录

   /*Result viewNumber(User user);//redis访问量查询*/

    Result register(User user);//注册用户

    Result editUserByUserName(User user);//修改用户密码，昵称通过用户名
}
