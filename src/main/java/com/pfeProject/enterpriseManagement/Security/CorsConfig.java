package com.pfeProject.enterpriseManagement.Security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Spécifiez les URL pour lesquelles vous autorisez les requêtes CORS
                .allowedOrigins("http://localhost:4200") // Autorisez les requêtes depuis ce domaine
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorisez les méthodes HTTP spécifiées
                .allowCredentials(true); // Autorisez l'envoi de cookies
    }
}
