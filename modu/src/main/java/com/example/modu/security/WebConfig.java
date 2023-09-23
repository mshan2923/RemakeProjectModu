package com.example.modu.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)//프론트에서도 axios > allowCredentials 허용해야함
                .maxAge(3600);
        //로그인후 request응답 헤더에 Origin, Access-Control-Request-Method,Access-Control-Request-Headers이 필요
    }
}
