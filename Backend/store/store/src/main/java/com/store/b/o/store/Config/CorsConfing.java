package com.store.b.o.store.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
@EnableWebMvc
public class CorsConfing  implements WebMvcConfigurer{

    @Override
    public void addCorsMappings (@NonNull CorsRegistry registry) { // Add @NonNull annotation here
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173") // URL del front
            .allowedMethods("*") // Metodos permitidos desde el front
            .allowCredentials(true); // Permitir credenciales
    };
};
