package com.cvbackend.springboot.maven.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/generate-pdf")
                    .allowedOrigins("http://localhost:3000", "http://cv.begby.net", "https://cv.begby.net")
                    .allowedMethods("POST")
                    .allowedHeaders("*")
                    .exposedHeaders("*");
            }
        };

    }
    
}
