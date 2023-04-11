package com.sa.youtube.dtos;

import org.springframework.web.multipart.MultipartFile;

public record UserForm(
        String name,
        String email,
        String password,
        MultipartFile profilePicture) {
}
