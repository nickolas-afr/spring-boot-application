package com.example.springbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootAppApplication {

    public static void main(String[] args) {

        System.out.println("Hello world");
        SpringApplication.run(SpringBootAppApplication.class, args);
    }

}
