package com.sa.youtube.dtos;

import com.sa.youtube.models.Category;
import com.sa.youtube.models.User;

import java.util.List;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String name,
        String description,
        Integer userCount,
        Integer videoCount,
        Long viewCount,
        List<VideoOutDTO> videoDTOList,
        List<UUID> userList
        ) {

    public CategoryDTO(Category category, List<VideoOutDTO> videoList) {
        this (
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getUserList().size(),
                videoList.size(),
                category.getViewCount(),
                videoList,
                category.getUserList().stream().map(User::getId).toList()
        );
    }

    public CategoryDTO(Category category) {
        this (
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getUserList().size(),
                null,
                null,
                null,
                category.getUserList().stream().map(User::getId).toList()
        );
    }



}
