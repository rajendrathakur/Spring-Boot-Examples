package com.springboot.examples.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @GetMapping
    public List<String> getUsers() {
        return Arrays.asList("Ajay", "Amar");
    }


}
