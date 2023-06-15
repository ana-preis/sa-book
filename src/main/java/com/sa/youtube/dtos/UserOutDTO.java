package com.sa.youtube.dtos;

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
                null//user.getSubscriptions().stream().map(Category::getId).toList()
            );
        }

        public UserOutDTO(User user, List<UUID> subs) {
            this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                subs
            );
        }
    }
