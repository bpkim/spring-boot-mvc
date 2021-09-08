package com.bpkim.springbootmvc.common.accounts;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Account saveAccount(Account account){
        System.out.println("pass "+ this.passwordEncoder.encode(account.getPassword()));

        System.out.println(passwordEncoder.matches(account.getPassword(), this.passwordEncoder.encode(account.getPassword())));
        account.setPassword(this.passwordEncoder.encode(account.getPassword()));
        return this.accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

//        Account account = new Account();
//        account.setEmail("admin@naver.com");
//        account.setUsername("admin");
//        account.setPassword(this.passwordEncoder.encode("qwer"));
//
//        Set<AccountRole> roles = new HashSet<>();
//        roles.add(AccountRole.ADMIN);
//        account.setRoles(roles);
//        System.out.println("User "+account.getEmail());
//        System.out.println("Password "+account.getPassword());
//        System.out.println(this.passwordEncoder.matches("qwer", account.getPassword()));
//        return new User(account.getEmail(), account.getPassword(), authorities(account.getRoles()));
        return account;
    }

    private Collection<? extends GrantedAuthority> authorities(Set<AccountRole> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_"+r.name()))
                .collect(Collectors.toSet());
        //"ROLE+"+
    }

}
