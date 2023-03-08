package com.springboot.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCachingEHCache3 {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCachingEHCache3.class, args);
    }

}