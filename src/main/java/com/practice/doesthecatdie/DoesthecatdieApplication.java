package com.practice.doesthecatdie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.practice.doesthecatdie.repositories")
public class DoesthecatdieApplication {


    public static void main(String[] args) {
        SpringApplication.run(DoesthecatdieApplication.class, args);

    }

}
