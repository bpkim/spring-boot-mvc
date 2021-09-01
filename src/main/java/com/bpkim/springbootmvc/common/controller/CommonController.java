package com.bpkim.springbootmvc.common.controller;

import com.bpkim.springbootmvc.common.Provider.JwtTokenProvider;
import com.bpkim.springbootmvc.common.accounts.Account;
import com.bpkim.springbootmvc.common.accounts.AccountRole;
import com.bpkim.springbootmvc.common.accounts.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class CommonController {

    @Autowired
    private final AccountService accountService;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;


    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestParam String id,
                                 @RequestParam String password) {
        Account account = (Account) accountService.loadUserByUsername(id);


        List<String> roleList = new ArrayList();
        Iterator roleIterator = account.getRoles().iterator();
        while(roleIterator.hasNext()){
            AccountRole role = (AccountRole)roleIterator.next();
            roleList.add(role.name());

        }

        return ResponseEntity.ok(jwtTokenProvider.createToken(String.valueOf(account.getId()), roleList));

    }


    @PostMapping(value = "/signup")
    public ResponseEntity signin(@RequestParam String id,
                                 @RequestParam String password,
                                 @RequestParam String name) {


        Set<AccountRole> roles = new HashSet<>();
        roles.add(AccountRole.ADMIN);


        Account account = accountService.saveAccount(Account.builder()
                .uid(id)
                .password(passwordEncoder.encode(password))
                .username(name)
                .roles(roles)
                .build());
        return ResponseEntity.ok(account);
    }
}