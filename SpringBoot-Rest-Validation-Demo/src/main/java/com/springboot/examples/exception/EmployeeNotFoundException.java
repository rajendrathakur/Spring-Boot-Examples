package com.springboot.examples.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(int id) {
        super(String.format("Employee id %d doesn't exist in our records", id));
    }
}
