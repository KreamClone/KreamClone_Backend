package com.sparta.kreamclone_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class KreamCloneBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KreamCloneBackendApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedOrigins("*")
//                        .allowedOrigins("http://localhost:8080", "http://localhost:3000", "http://127.0.0.1:3000", "http://hanghae66.s3-website.ap-northeast-2.amazonaws.com")
                        .allowedOriginPatterns("*")
                        .exposedHeaders("ACCESS_KEY", "REFRESH_KEY", "Authorization", "USER_ROLE")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH")
                        //.allowedHeaders()
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
