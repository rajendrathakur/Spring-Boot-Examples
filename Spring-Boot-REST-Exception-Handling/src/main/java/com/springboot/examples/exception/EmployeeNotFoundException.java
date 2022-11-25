package com.springboot.examples.exception;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(UUID id) {
        super("Employee '"+ id + "' doesn't exist in our records");
    }
}
