package com.sa.youtube.infra.errors;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<?> error404Handler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400Handler(MethodArgumentNotValidException e) {
        List<ErrorDTO> errList = e.getFieldErrors().stream().map(ErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(errList);
    }

    @ExceptionHandler({GeneralSecurityException.class, IOException.class, GoogleJsonResponseException.class})
    public ResponseEntity<?> error5xxHandler() {
        return ResponseEntity.internalServerError().build();
    }

}