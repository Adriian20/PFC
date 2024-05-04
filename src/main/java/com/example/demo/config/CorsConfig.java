package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000", "http://192.168.1.138:3000") // Agrega las URLs de origen permitidas
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Encabezados HTTP permitidos
                .exposedHeaders("Authorization") // Cabezal de respuesta permitido
                .allowCredentials(true); // Permitir credenciales (cookies)
    }
}

