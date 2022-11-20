package com.springboot.examples.trainingtutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TrainingtutorialsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingtutorialsApplication.class, args);
	}

	@GetMapping("/")
	public String testAPI() {
		return "Hello World";
	}

}
