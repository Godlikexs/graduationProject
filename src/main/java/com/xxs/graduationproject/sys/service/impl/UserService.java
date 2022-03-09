package com.xxs.graduationproject.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.mapper.UserMapper;
import com.xxs.graduationproject.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(User user) {
        Result result = new Result();
        if (StringUtils.isEmpty(user.getUserName())){
            return new Result(400,"账号不能为空","");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            return new Result(400,"密码不能为空","");
        }
        //利用shiro框架实现登录
        //获取shiro主题，可以理解为当前系统用户  调用自定义Realms中认证方法
        Subject subject = SecurityUtils.getSubject();
        //通过登录名查询用户
        if(!subject.isAuthenticated()){
            //创建用户名密码token
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
            try{
                //让shiro框架帮我们执行登录
                subject.login(token);
            }catch (UnknownAccountException e){
                //捕获一系列shiro框架抛出的异常，账号不存在
                e.printStackTrace();
                result.setCode(400);
                result.setMessage(user.getUserName()+"的账号不存在");
                return result;
            }catch (IncorrectCredentialsException e){
                //不正确的凭证异常，用户名存在，当时用户输入的和系统中的不一致
                e.printStackTrace();
                result.setCode(400);
                result.setMessage(user.getUserName()+"的密码不正确");
                return result;
            }catch (AuthenticationException e){
                //通用的认证异常，realm中代码编写错误
                e.printStackTrace();
                result.setCode(500);
                result.setMessage( "用户登录失败");
                return result;
            }
        }
        //跳过异常信息登录成功
        //获取已经登录成功后的当前用户信息, 这时principal就是user对象
        Object principal = subject.getPrincipal();
        User loginUser = (User) principal;
        //获取shiro的session对象
        Session session = subject.getSession();
        //使用session对象共享数据,当前已登录的用户信息
        session.setAttribute("loginUser", loginUser);

        result.setData(principal);
        result.setCode(200);
        result.setMessage("用户登录成功");
        return result;

    }
}

