package com.example.projet3chatop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication
@EnableJpaAuditing

@EnableWebMvc
public class Projet3ChaTopApplication implements WebMvcConfigurer {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(Projet3ChaTopApplication.class, args);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:".concat(environment.getProperty("java.io.tmpdir", "").concat("/")));
    }

}
