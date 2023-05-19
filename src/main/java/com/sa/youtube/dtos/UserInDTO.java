package com.sa.youtube.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserInDTO(
    @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank String password
) {}
