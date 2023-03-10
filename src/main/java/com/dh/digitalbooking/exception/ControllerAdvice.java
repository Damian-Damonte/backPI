package com.dh.digitalbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequest> badRequestExceptionHandler(BadRequestException e) {
        return ResponseEntity.badRequest().body(new BadRequest(e.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFound> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFound(e.getMessage()));
    }
}
