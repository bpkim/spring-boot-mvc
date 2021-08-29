package com.bpkim.springbootmvc.common.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public String createAccount(AccountDto accountDto){ // 회원 추가

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
}
