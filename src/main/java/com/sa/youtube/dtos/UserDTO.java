package com.sa.youtube.dtos;

import com.sa.youtube.models.File;
import com.sa.youtube.models.User;

import java.util.UUID;


public record UserDTO(
        UUID id,
        String name,
        String email,
        File profilePicture) {

    public UserDTO(User user) {
        this(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getProfilePicture()
        );
    }
}
