package com.springboot.examples.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User '" + id + "' doesn't exist in our records");
    }
}
