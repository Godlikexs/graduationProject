package com.xxs.graduationproject.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.xxs.graduationproject.realams.MyShiroRealms;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class MyShiroConfig {
    /**
     * 配置密码匹配器
     * @return
     */
    @Bean(name={"hashedCred"})
    public HashedCredentialsMatcher getMatcher(){
        HashedCredentialsMatcher hsc = new HashedCredentialsMatcher();
        //加密算法
        hsc.setHashAlgorithmName("MD5");
        //加密次数
        hsc.setHashIterations(1024);
        return hsc;
    }
    /**
     * 配置自定义的 realm
     * @param hs
     * @return
     */
    @Bean(name={"myRealm"})
    public MyShiroRealms getRealm(@Qualifier("hashedCred") HashedCredentialsMatcher hs ){
        MyShiroRealms realm = new MyShiroRealms();
        realm.setCredentialsMatcher(hs);
        return realm;
    }
    /**
     * 配置安全管理类
     * @return
     */
    @Bean(name={"securityManager"})
    public DefaultWebSecurityManager getSecurity(@Qualifier("myRealm") MyShiroRealms realm){
        DefaultWebSecurityManager dm = new DefaultWebSecurityManager();
        dm.setRealm(realm);
        return dm;
    }

    @Bean
    public LifecycleBeanPostProcessor getProcess(){
        return new LifecycleBeanPostProcessor();
    }

    //html 标签配置
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    /**
     * 配置权限
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager sm){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(sm);
        //配置必须登录
        bean.setLoginUrl("/error/login");
        //配置无此权限访问页面
        bean.setUnauthorizedUrl("/error/unAuth");

        //配置权限的map
        HashMap<String, String> map = new HashMap<String, String>();

        //登录匿名访问
        map.put("/api/**","anon");

        map.put("/**","authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
}