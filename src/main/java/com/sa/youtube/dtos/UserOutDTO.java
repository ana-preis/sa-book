package com.sa.youtube.dtos;

import com.sa.youtube.models.Category;
import com.sa.youtube.models.User;

import java.util.List;
import java.util.UUID;


public record UserOutDTO(

        UUID id,
        String username,
        String email,
        List<UUID> subscriptionsIDs

    ) {

        public UserOutDTO(User user) {
            this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getSubscriptions().stream().map(Category::getId).toList()
            );
        }
    }
