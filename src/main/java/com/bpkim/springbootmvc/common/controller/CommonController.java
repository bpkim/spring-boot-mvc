package com.bpkim.springbootmvc.common.controller;

import com.bpkim.springbootmvc.common.accounts.Account;
import com.bpkim.springbootmvc.common.accounts.AccountDto;
import com.bpkim.springbootmvc.common.accounts.AccountRole;
import com.bpkim.springbootmvc.common.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@Controller
public class CommonController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @PostMapping("/user")
    public String signup(AccountDto accountDto) { // 회원 추가
        Account account = new Account();
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());


        if(AccountRole.ADMIN.name().equals(accountDto.getRoles().toUpperCase())){
            account.setRoles(Set.of(AccountRole.ADMIN));
        }else if(AccountRole.USER.name().equals(accountDto.getRoles().toUpperCase())){
            account.setRoles(Set.of(AccountRole.USER));
        }else{

            account.setRoles(Set.of(AccountRole.ADMIN, AccountRole.USER));
        }
        System.out.println("accountDto.getRole() "+accountDto.getRoles());
        account.getRoles().stream().forEach(accountRole -> {
            System.out.println(accountRole.name());
        });
        accountService.saveAccount(account);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/adminonly")
    public String adminonly(){
        return "Secret Page";
    }

}
