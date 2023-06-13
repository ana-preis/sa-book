package com.sa.youtube.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserNameUpdateDTO(@NotBlank String username) {}
