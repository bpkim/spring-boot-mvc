package com.bpkim.springbootmvc.common.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Profile("local")
public class UserController {

    @ResponseBody
    @RequestMapping("/user/info")
    public Object userInfo(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
