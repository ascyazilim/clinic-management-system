package com.asc.clinicms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // React dev server
                .allowedOrigins("http://localhost:5173")

                // Preflight dahil tüm methodlar
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")

                // JWT için Authorization şart, content-type da lazım
                .allowedHeaders("Authorization", "Content-Type", "Accept", "Origin", "X-Requested-With")

                // Frontend’in okuyabilmesini istediğin headerlar (opsiyonel ama faydalı)
                .exposedHeaders("Authorization")

                // Cookie kullanmıyorsak false kalsın
                .allowCredentials(false)

                // Preflight cache (saniye): 1 saat
                .maxAge(3600);
    }
}
