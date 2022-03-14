package com.xxs.graduationproject.realams;

import com.xxs.graduationproject.sys.entity.Power;
import com.xxs.graduationproject.sys.entity.Role;
import com.xxs.graduationproject.sys.entity.User;
import com.xxs.graduationproject.sys.mapper.PowerMapper;
import com.xxs.graduationproject.sys.mapper.RoleMapper;
import com.xxs.graduationproject.sys.mapper.UserMapper;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//shiro框架找我们的系统要认证相关信息
public class MyShiroRealms extends AuthorizingRealm {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private PowerMapper powerMapper;

    @Autowired
    public void setPowerMapper(PowerMapper powerMapper) {
        this.powerMapper = powerMapper;
    }

    @Override//认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();//获取当前用户输入的登录名
        User user = userMapper.queryOneByUserName(username);//用户信息及角色信息
        if(user==null){
            //没有找到对应的用户
            throw new UnknownAccountException("没有对应用户"+username);
        }
        //创建认证信息
        //三参数构造方法 第一个参数当前用户信息user 第二个参数：系统中保存暗文密码 第三个参数:salt 第四个参数  固定 当前realm的名称 getName()
        ByteSource salt = ByteSource.Util.bytes(user.getSolt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(), salt,getName());
        //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return info;
    }
    //对于已经认证过后的用户，只要再验证需要权限相关信息的时候，shiro会回调该方法
    //角色相关和权限相关
    @Override//授权信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //返回当前已登录的用户的角色和权限信息
        //获取当前已登录的用户
        User loginUser = (User) principals.getPrimaryPrincipal();// return info;
        //记录角色信息
        Set<String> roles = new HashSet<>();
        //记录权限信息（未完善）
        Set<String> perms = new HashSet<>();
        //遍历当前登录的用户的角色信息，组织信息
        if(loginUser.getRoles()!=null){
            for (Role one : loginUser.getRoles()) {
                //使用角色名作为shiro系统的一类权限信息
                roles.add(one.getRoleName());

            }
        }
        //查询权限通过角色名
/*        List<Power> powers = powerMapper.queryOneByRoleName(one.getRoleName());
        for (Power power : powers) {
            perms.add(power);
        }*/
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(perms);
        return simpleAuthorizationInfo;
    }


}
