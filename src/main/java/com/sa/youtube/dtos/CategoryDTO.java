package com.sa.youtube.dtos;

import com.sa.youtube.models.Category;

import java.util.List;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String name,
        Long viewCount,
        String description,
        List<VideoDTO> videoDTOList) {

    public CategoryDTO(Category category, List<VideoDTO> videoList) {
        this (
                category.getId(),
                category.getName(),
                category.getViewCount(),
                category.getDescription(),
                videoList
        );
    }

}
