package com.evandro.client.exceptions.handle;

import com.evandro.client.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class StandardHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException re, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(StandardError.builder()
            .timestamp(Instant.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Resource not found")
            .message(re.getMessage())
            .path(http.getRequestURI())
            .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> entityNotFound(MethodArgumentTypeMismatchException me, HttpServletRequest http) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Resource not found")
                .message(me.getMessage())
                .path(http.getRequestURI())
                .build());
    }
}
