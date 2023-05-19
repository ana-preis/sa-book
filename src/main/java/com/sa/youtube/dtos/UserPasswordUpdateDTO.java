package com.sa.youtube.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserPasswordUpdateDTO(
    @NotBlank String oldPassword,
    @NotBlank String newPassword
) {}
