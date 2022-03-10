package com.xxs.graduationproject.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.mapper.UserMapper;
import com.xxs.graduationproject.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxs.graduationproject.utils.EmailSend;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private Result result;//注入响应结果集

    @Resource
    private UserMapper userMapper;

    @Resource//注入redis
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private EmailSend emailSend;//注入邮箱工具类

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

    @Override//实现邮箱登录
    public Result emailLogin(User user, HttpSession httpSession) {
        //调用工具类执行登录业务 @Autowired
        //生成四位随机数
        String a="";
        for (int i = 0; i < 4; i++) {
            int code = new Random().nextInt(10);
            a+=code;
        }

        boolean send = emailSend.send(user.getEmail(), "小松网", "您的验证码是"+a);
        if(send){//如果为真，发送成功

            System.out.println("发送成功");
            result.setData(a);
            result.setCode(200);
            result.setMessage("邮箱发送成功");
            //放入会话域 设置失效时间
            httpSession.setAttribute("code",a);
            httpSession.setMaxInactiveInterval(60000);
        }else{
            System.out.println("发送失败");
            result.setData(a);
            result.setCode(500);
            result.setMessage("邮箱发送失败");
        }
        return result;
    }

    @Override
    public Result phoneLogin(User user) {
        //密码次数错误的键名
        String keyWord = user.getUserName()+":num";
        String keyWordLock = user.getUserName()+":lock";
        //判断是否锁定
        Object o = redisTemplate.opsForValue().get(keyWordLock);
        if(o!=null){
            //获取失效事件
            Long time = redisTemplate.getExpire(keyWordLock,TimeUnit.SECONDS);
            result.setCode(500);
            result.setMessage("你的账号被锁定,还剩余"+time+"秒");
            return result;
        }
        if(user.getUserName().equals("admin")&&user.getPassword().equals("123456")){
            //删除缓存
            redisTemplate.delete(keyWord);
            redisTemplate.delete(keyWordLock);
            //登陆成功
            //存入用户信息
            redisTemplate.opsForValue().set(user.getUserName()+":info",user,30,TimeUnit.MINUTES);
            //代替session会话域
            result.setCode(200);
            result.setMessage("登陆成功");

        }else{
            //记录错误一次
            redisTemplate.opsForValue().increment(keyWord,1);
            //获取设置次数

            Integer integer = (Integer) redisTemplate.opsForValue().get(keyWord);
            if (integer==3){
                //删除错误次数
                redisTemplate.delete(keyWord);
                result.setCode(500);
                redisTemplate.opsForValue().set(keyWordLock,5,2, TimeUnit.MINUTES);
                result.setMessage("你的密码输入错误达到上限，已经锁定,俩分钟后尝试");
            }else{
                result.setCode(500);
                result.setMessage("你的密码输入错误剩余"+(3-integer));
            }
        }


        return result;
    }
}

