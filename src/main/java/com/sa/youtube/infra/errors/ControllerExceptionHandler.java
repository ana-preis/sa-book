package com.sa.youtube.infra.errors;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<?> error404handler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400handler(MethodArgumentNotValidException e) {
        List<ErrorDTO> errList = e.getFieldErrors().stream().map(ErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(errList);
    }

}