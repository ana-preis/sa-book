package com.sa.youtube.dtos;

import com.sa.youtube.models.User;

import java.util.UUID;


public record UserOutDTO(

        UUID id,
        String username,
        String email

    ) {

        public UserOutDTO(User user) {
            this(
                user.getId(),
                user.getName(),
                user.getEmail()
            );
        }
    }
