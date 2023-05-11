package com.sa.youtube.dtos;

import com.sa.youtube.models.Category;

import java.util.List;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String name,
        String description,
        Integer userCount,
        Integer videoCount,
        Long viewCount,
        List<VideoDTO> videoDTOList) {

    public CategoryDTO(Category category, List<VideoDTO> videoList) {
        this (
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getUserList().size(),
                category.getVideoList().size(),
                category.getViewCount(),
                videoList
        );
    }

}
