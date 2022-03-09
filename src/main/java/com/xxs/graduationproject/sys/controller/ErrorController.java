package com.xxs.graduationproject.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/login")
    public String noLogin(){
        return "/500";
    }

    @RequestMapping("/loginView")
    public String loginView(){
        return "/login";
    }

    @RequestMapping("/unAuth")
    public String unAuth(){
        return "/unAuth";
    }

}
