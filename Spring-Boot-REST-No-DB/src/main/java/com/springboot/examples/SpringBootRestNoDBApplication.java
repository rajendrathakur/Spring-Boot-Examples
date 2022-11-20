package com.springboot.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootRestNoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestNoDBApplication.class, args);
    }

    @GetMapping("/")
    public String testAPI() {
        System.out.println("jfdjfdfd");
        return "hello Mahesh";
    }

}
