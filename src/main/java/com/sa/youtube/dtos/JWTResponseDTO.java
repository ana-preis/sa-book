package com.sa.youtube.dtos;

import com.sa.youtube.infra.security.Token;

import jakarta.validation.constraints.NotBlank;

public record JWTResponseDTO(

    @NotBlank
    String accessToken,

    @NotBlank
    String refreshToken,

    @NotBlank
    String email

) {

    public JWTResponseDTO(Token token) {
        this(
            token.getAccessToken(),
            token.getRefreshToken(),
            token.getUser().getEmail()
        );
    }

}
