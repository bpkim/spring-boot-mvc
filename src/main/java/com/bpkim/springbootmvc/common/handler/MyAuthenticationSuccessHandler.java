package com.bpkim.springbootmvc.common.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess ! ");
        System.out.println("권한 리스트");
        System.out.println(authentication.getAuthorities());
        System.out.println("사용자 정보 ");
        System.out.println(authentication.getPrincipal());

        // 권한 리스트 추출
//        Collection<GrantedAuthority> authList = authentication.getAuthorities();
//        Iterator<GrantedAuthority> it = authList.iterator();
//
//        while(it.hasNext()){
//
//        }
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userNm", authentication.getName());

        // 권한 유형에 따라 화면 분기

        httpServletResponse.sendRedirect("/home");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess 2! ");
    }
}
