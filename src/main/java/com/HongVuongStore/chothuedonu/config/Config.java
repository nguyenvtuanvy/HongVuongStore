package com.HongVuongStore.chothuedonu.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Áp dụng cho tất cả các endpoint
                        .allowedOrigins("http://localhost:3000") // Cho phép yêu cầu từ http://localhost:3000
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức HTTP
                        .allowedHeaders("*"); // Cho phép tất cả các headers
            }
        };
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(10)); // Giới hạn kích thước file: 10MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(10)); // Giới hạn kích thước yêu cầu: 10MB
        return factory.createMultipartConfig();
    }
}
