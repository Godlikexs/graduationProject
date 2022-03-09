package com.xxs.graduationproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//下面是springboot的跨域配置类，springboot版本 必须是 2.4 以前的版本
@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")/*所有的当前站点的请求地址，都支持跨域访问*/
                .allowedOriginPatterns("*")/*  2.6版本修改方法*/
                //.allowedOrigins("*")/*所有的外部域都可跨域访问*/
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")/*哪些请求 需要跨域配置*/
                .allowCredentials(true) /*是否支持跨域用户凭证*/
                .maxAge(300)/*超时时长设置为5分钟。 时间单位是秒。*/
                .allowedHeaders("*");/*请求体的头部*/

    }
}
