package com.springboot.examples.exception;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(long id) {
        super("Employee '"+ id + "' doesn't exist in our records");
    }
}
