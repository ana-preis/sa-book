package com.sa.youtube.dtos;

import com.sa.youtube.models.User;

import java.util.UUID;


public record UserDTO(

        UUID id,
        String username,
        String email

    ) {

        public UserDTO(User user) {
            this(
                user.getId(),
                user.getUsername(),
                user.getEmail()
            );
        }
    }
