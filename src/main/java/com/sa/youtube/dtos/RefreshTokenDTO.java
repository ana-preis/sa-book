package com.sa.youtube.dtos;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDTO(
    @NotBlank String refreshToken
) {}
