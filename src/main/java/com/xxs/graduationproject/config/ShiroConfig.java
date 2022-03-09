package com.xxs.graduationproject.config;


import com.xxs.graduationproject.realams.MyShiroRealms;
import com.xxs.graduationproject.sys.mapper.UserMapper;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ShiroConfig {

    @Bean//配置凭证匹配器
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }
    @Bean//配置自定义的shiro域
    public MyShiroRealms myShiroRealms(@Autowired UserMapper userMapper,
                                       @Qualifier("credentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        MyShiroRealms myShiroRealms = new MyShiroRealms();
        myShiroRealms.setUserMapper(userMapper);
        myShiroRealms.setCredentialsMatcher(hashedCredentialsMatcher);
        return myShiroRealms;

    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myShiroRealms") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }
    //配置过滤器，（——----------  自上而下 ---------------)
    // shiro会根据添加的Definition的先后顺序执行验证，满足过滤结束
    @Bean
    public DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        //不做配置，任何资源都行
        //登录请求的url匿名访问
        defaultShiroFilterChainDefinition.addPathDefinition("/api/login","anon");//左边第一个参数配置url样式

        return  defaultShiroFilterChainDefinition;
    }
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        System.err.println("--------------shiro已经加载----------------");
        return defaultWebSecurityManager;
    }

}
