package com.springboot.examples.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime localDateTime;
    private String message;

    public ErrorResponse(String message) {
        this.localDateTime = LocalDateTime.now();
        this.message = message;
    }
}
