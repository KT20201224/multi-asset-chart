package com.multiasset.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // API 경로만 허용
                .allowedOrigins("http://localhost:5173",
                        "https://charts.io.kr/") // 프론트 주소
                .allowedMethods("GET", "POST", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}