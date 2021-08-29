package com.bpkim.springbootmvc.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 에러페이지
        registry.addResourceHandler("/error/**")
                .addResourceLocations("classpath:templates/error/");
    }
}
