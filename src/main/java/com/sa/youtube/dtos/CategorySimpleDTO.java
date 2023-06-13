package com.sa.youtube.dtos;

import java.util.UUID;

import com.sa.youtube.models.Category;

public record CategorySimpleDTO(UUID id, String name) {

    public CategorySimpleDTO(Category category) {
        this (
            category.getId(),
            category.getName()
        );
    }

}
