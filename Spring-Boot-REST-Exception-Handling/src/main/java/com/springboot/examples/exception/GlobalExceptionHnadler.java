package com.springboot.examples.exception;

import com.springboot.examples.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHnadler {

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
      ErrorResponse errorResponse = new ErrorResponse(ex.getLocalizedMessage());
      return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
