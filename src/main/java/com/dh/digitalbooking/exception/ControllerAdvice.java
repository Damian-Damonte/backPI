package com.dh.digitalbooking.exception;

import com.dh.digitalbooking.dto.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> badRequestExceptionHandler(BadRequestException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(400).addError(e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(404).addError(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ExceptionResponse response = new ExceptionResponse(400);
        e.getBindingResult().getFieldErrors().forEach(error -> response.addError(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionResponse> forbiddenExceptionHandler(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(403).addError(e.getMessage()));
    }
}
