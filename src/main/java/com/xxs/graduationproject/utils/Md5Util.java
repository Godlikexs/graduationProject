package com.xxs.graduationproject.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/*
* shiro加密* */
public class Md5Util {
    /*
     * 将明文密码散列生成暗文密码
     * salt 盐 增加散列之后密码强度
     * */
    public static String md5(String password, String salt) {
        //第一参数：算法名
        //第二参数：明文密码
        //第三参数: salt
        //第四个次数：迭代次数
        SimpleHash md5 = new SimpleHash("MD5", password, salt, 1024);
        return md5.toString();//返回暗文密码
    }

    public static void main(String[] args) {
        String password = "179516";
        String salt ="90";
        String s = md5(password, salt);
        System.out.println(s);
    }

}