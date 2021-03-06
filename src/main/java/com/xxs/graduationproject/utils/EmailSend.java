package com.xxs.graduationproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component//采用spring组件不能自己new对象 @VALUE会失效
public  class EmailSend {
    //注入邮件发送服务类
    @Autowired
     JavaMailSender javaMailSender;


    private  String myEmail;

    @Value("${spring.mail.username}")
    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    /**
     *
     * @param toEmail 收件人
     * @param subject 标题
     * @param content  邮件正文
     * @return
     */
    public boolean send(String toEmail,String subject,String content){
    //信封
    SimpleMailMessage message = new SimpleMailMessage();
    //发件人
        message.setFrom(myEmail);
    //收件人
        message.setTo(toEmail);
    //标题
        message.setSubject(subject);
    //正文
        message.setText(content);
        try{
        javaMailSender.send(message);
        return true;
    }catch (Exception e){
        e.printStackTrace();
    }

        return false;
}
}
