package com.springboot.examples.exception;

import com.springboot.examples.controller.EmployeeController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            Logger.getLogger(EmployeeController.class.getName());

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(RuntimeException ex, WebRequest request) {
        ErrorMessage response = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND,
                request.getDescription(false), Arrays.asList(ex.getMessage()));
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(RuntimeException ex, WebRequest request) {
        log.info("Exception occurred with thread: "+ Thread.currentThread().getName());
        ErrorMessage response = new ErrorMessage(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR,
                request.getDescription(false), Arrays.asList(ex.getMessage()));
        return response;
    }
}
