package com.sa.youtube.dtos;

import com.sa.youtube.models.Category;
import com.sa.youtube.models.Video;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
