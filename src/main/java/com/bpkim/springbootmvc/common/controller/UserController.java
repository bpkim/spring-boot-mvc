package com.bpkim.springbootmvc.common.controller;

import com.bpkim.springbootmvc.common.accounts.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@Profile("local")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @GetMapping("/user/info")
    public Object userInfo(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    @ResponseBody
    @GetMapping("/user/auth")
    public Object  userAuth(@AuthenticationPrincipal Account account){
        logger.info(account.getUsername());
        logger.info(account.getEmail());
        logger.info(account.getId().toString());
        return account;
    }
/*

    @GetMapping(value = "/sample")
    public void sample(@AuthenticationPrincipal MemberDTO memberDTO, Model model){

        log.info("Sample....");
        log.info(memberDTO);

        model.addAttribute("member", memberDTO);
    }
    <div th:text="${member}"></div>
*/

}
