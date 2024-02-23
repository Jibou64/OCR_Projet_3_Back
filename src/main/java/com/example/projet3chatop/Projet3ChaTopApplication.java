package com.example.projet3chatop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Projet3ChaTopApplication {

    public static void main(String[] args) {
        SpringApplication.run(Projet3ChaTopApplication.class, args);
    }

}
