package com.sparta.voyageblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VoyageBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoyageBlogApplication.class, args);
    }

}
