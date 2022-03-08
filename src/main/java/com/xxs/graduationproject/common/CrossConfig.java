package com.xxs.graduationproject.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")/*所有的当前站点的请求地址，都支持跨域访问*/
                .allowedOrigins("*")/*所有的外部域都可跨域访问*/
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")/*哪些请求 需要跨域配置*/
                .allowCredentials(true) /*是否支持跨域用户凭证*/
                .maxAge(300)/*超时时长设置为5分钟。 时间单位是秒。*/
                .allowedHeaders("*");/*请求体的头部*/
    }
}
