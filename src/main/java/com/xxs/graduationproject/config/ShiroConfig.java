package com.xxs.graduationproject.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.xxs.graduationproject.realams.MyShiroRealms;
import com.xxs.graduationproject.sys.mapper.UserMapper;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *   Shiro相关配置bean
 * */
@Configuration
public class ShiroConfig {

    //配置凭证匹配器
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(1024);
        return matcher;
    }

    //配置自定义的shiro域
    @Bean
    public MyShiroRealms myRealm(@Autowired UserMapper userMapper,
                                @Qualifier("credentialsMatcher") CredentialsMatcher matcher){
        MyShiroRealms realm = new MyShiroRealms();
        //realm设置凭证匹配器对象
        realm.setCredentialsMatcher(matcher);
        realm.setUserMapper(userMapper);
        return realm;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }
    //配置shiro securityManager
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm") Realm realm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    //配置过滤链：shiro会根据添加的Definition的先后顺序执行验证，如果满足一条规则，过滤结束
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        //不做配置，任何资源都放行
        //第一个参数配置url的样式， 第二个参数过滤器
        // anon 匿名可以访问
        // authc 成功认证的用户可以访问
        // roles[角色名] 具有某角色的用户可以访问
        // perms[权限名] 具有某权限的用户可以访问

        //登录请求的url匿名访问
        definition.addPathDefinition("/api/login", "anon");
        definition.addPathDefinition("/api/emailLogin", "anon");
        //静态资源都是匿名可以访问

        //剩余后台资源，需要认证来能访问
        definition.addPathDefinition("/**", "authc");
        return definition;
    }

    //thymeleaf模板中的shiro属性或标签生效
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

}
