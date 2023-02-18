package com.springboot.examples;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employee API", description = "Employee CRUD Oeerations", version = "1.0.0"))
public class SpringBootRestSwaggerOpenAPIV3 {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestSwaggerOpenAPIV3.class, args);
    }
}