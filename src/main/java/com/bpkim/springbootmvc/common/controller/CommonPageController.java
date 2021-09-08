package com.bpkim.springbootmvc.common.controller;

import com.bpkim.springbootmvc.common.accounts.AccountService;
import com.bpkim.springbootmvc.common.userlog.MoveLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CommonPageController {

    @Autowired
    AccountService accountService;

    @MoveLogging
    @RequestMapping("/home")
    public String home(){

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();


        return "home";
    }

    @MoveLogging
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @MoveLogging
    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }

    @MoveLogging
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
/*

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
*/

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        // 세션 삭제
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
