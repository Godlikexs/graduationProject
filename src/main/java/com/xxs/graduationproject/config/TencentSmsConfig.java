package com.xxs.graduationproject.config;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description  短信发送配置类
 * @Autor xxs
 * @Date 2021/3/10 10:09
 * @Version 1.0
 **/
@Configuration
public class TencentSmsConfig {
    @Bean
    public SmsClient getSmsClient() {
        //创建腾讯云短信发送凭证对象，填入secretId和secretKey
        Credential cred = new Credential("*******************", "*******************");

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        //创建短信发送服务平台对象
        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
        return client;
    }
}
