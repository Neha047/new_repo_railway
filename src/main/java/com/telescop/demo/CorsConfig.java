package com.telescop.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Add your Angular application's domain
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Add allowed HTTP methods
                .allowedHeaders("*"); // Add allowed headers
    }
}
