package com.springboot.examples.controller;

import com.springboot.examples.model.UserInfo;
import com.springboot.examples.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users/")
@Validated
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Integer> createUser(@RequestBody @Valid UserInfo userInfo) {
        return new ResponseEntity<Integer>(userService.createUser(userInfo), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserInfo> fetchuser(@PathVariable @Max(5) int id) {
            return new ResponseEntity<>(userService.fetchUser(id), HttpStatus.OK);
    }

}
