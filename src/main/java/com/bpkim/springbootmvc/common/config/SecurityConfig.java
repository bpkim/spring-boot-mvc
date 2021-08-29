package com.bpkim.springbootmvc.common.config;

import com.bpkim.springbootmvc.common.accounts.AccountRole;
import com.bpkim.springbootmvc.common.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{



    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    // 스프링 시큐리티 필터에 걸린다.
    @Override
    public void configure(WebSecurity web) throws Exception {
        //   docs/index 시큐리티 미적용

        web.ignoring().mvcMatchers("/docs/index.html");
        //   기본 위치에는 시큐리티 미적용
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers( "/h2-console/**");
    }

    // 시큐리티 필터 지나서 들어온것
    // http 로 거르면 스프링 시큐리티 안으로 들어온것임
    // 그 요청에 어노미 하면 아무나 접근할 수 있는 요청
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                    .mvcMatchers("/docs/index.html").anonymous()
//                    .mvcMatchers("/docs/index.html").anonymous()
//                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/user","/api/account").permitAll() // 누구나 접근 허용

                .mvcMatchers("/adminonly").hasAnyRole(AccountRole.ADMIN.name())


                .mvcMatchers("/home").permitAll()
//                .antMatchers("/**").permitAll()  // 넓은 범위의 URL을 아래에 배치한다.
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                // 로그인 성공시 url
                .successForwardUrl("/home")
//                .successHandler(new MyAuthenticationSuccessHandler())// 이걸 활성화 하면 로그인 성공시 url 으로 안간다
//                .failureHandler(new MyAuthenticationFailureHandler())
                // 로그인 실패시 url
//                .failureForwardUrl("/home")
                // formLogin: 다른 옵션 설정이 없는 경우 시큐리티가 제공하는 기본 로그인 form 페이지 사용
                .and()
                .logout().logoutSuccessUrl("/login")
                .invalidateHttpSession(true) // 세션 날리기
        ;
        // 로그아웃 기본 url은 (/logout)
        // 새로 설정하려면 .logoutUrl("url") 사용

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
                .passwordEncoder(passwordEncoder);


/*
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("1234")).roles(AccountRole.ADMIN.name())
                .and()
                .withUser("guest").password(passwordEncoder().encode("guest")).roles(AccountRole.USER.name());*/

    }
}