package com.amaris.technicaltest.config;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;
=======
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

<<<<<<< HEAD
    @Value("${com.technical.test.frontend.url}")
    private String frontedUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontedUrl)
=======
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Origen del frontend
>>>>>>> cff0e96d62514e87f4fad1dd69bb871130958161
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
