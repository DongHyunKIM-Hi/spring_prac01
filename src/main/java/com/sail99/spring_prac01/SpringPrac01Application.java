package com.sail99.spring_prac01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPrac01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringPrac01Application.class, args);
    }

}
