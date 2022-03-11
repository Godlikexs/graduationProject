package com.xxs.graduationproject.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.xxs.graduationproject.common.Result;
import com.xxs.graduationproject.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description 短信发送工具类
 * @Autor xxs
 * @Date 2021/3/10
 * @Version 1.0
 **/
@Component
public class TenxunSmsSend {
    @Resource
    private SmsClient smsClient;

    @Resource
    private Result result;

    public Result send(User user)throws TencentCloudSDKException{
        HashMap<String, Object> hashMap = new HashMap<>();
        //创建短信对象  自动装配result
        //创建短信发送请求对象
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        //手机号
        String[] userPhone = {user.getPhone()};
        //接收短信手机号
        sendSmsRequest.setPhoneNumberSet(userPhone);
        //个人短信模板模板Id
        sendSmsRequest.setTemplateId("********");
        //个人短信标题
        sendSmsRequest.setSignName("********");
        //个人短信发送SDK
        sendSmsRequest.setSmsSdkAppId("********");
        //生成四位随机数
        String a="";
        for (int i = 0; i < 4; i++) {
            int code = new Random().nextInt(10);
            a+=code;
        }
        String[] codes = {a};
        sendSmsRequest.setTemplateParamSet(codes);
        try {
            SendSmsResponse sendSmsResponse = smsClient.SendSms(sendSmsRequest);
            System.out.println(SendSmsResponse.toJsonString(sendSmsResponse));
            ObjectMapper mapper = new ObjectMapper();
            try {
                HashMap<String,Object> map = mapper.readValue(SendSmsResponse.toJsonString(sendSmsResponse),HashMap.class);
                List<HashMap<String,Object>> list = (List<HashMap<String,Object>> )map.get("SendStatusSet");
                if (list.get(0).get("Code").equals("Ok")){
                    result.setCode(200);
                    result.setData(a);
                    result.setMessage("腾讯短信发送成功");
                }else{
                    result.setCode(500);
                    result.setMessage("腾讯短信发送失败");
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return result;
    }
}
