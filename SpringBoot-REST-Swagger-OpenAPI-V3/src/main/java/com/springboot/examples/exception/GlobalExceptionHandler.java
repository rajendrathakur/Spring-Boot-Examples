package com.springboot.examples.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorMessage handleResourceNotFoundException(RuntimeException ex, WebRequest request) {
        ErrorMessage response = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND,
                request.getDescription(false), Arrays.asList(ex.getMessage()));
        return response;
    }
}
