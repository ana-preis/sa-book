package com.sa.youtube.infra.errors;

import org.springframework.validation.FieldError;

public record ErrorDTO(String field, String msg) {

    public ErrorDTO(FieldError err) {
        this(
            err.getField(),
            err.getDefaultMessage()
        );
    }

}