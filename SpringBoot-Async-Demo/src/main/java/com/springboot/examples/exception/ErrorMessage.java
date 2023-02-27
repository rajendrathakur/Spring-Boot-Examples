package com.springboot.examples.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY hh:mm:ss")
    private LocalDateTime timeStamp;
    private HttpStatus status;
    private String path;
    private List<String> errors;

}
