package com.sa.youtube.controllers.dtos;

import java.util.Base64;
import java.util.UUID;


public record UserDTO(
        UUID id,
        String name,
        String email,
        String password,
        Base64 profilePicture
) {
}
