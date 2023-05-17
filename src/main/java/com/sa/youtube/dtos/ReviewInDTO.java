package com.sa.youtube.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;


public record ReviewInDTO(

    @NotNull
    UUID userId,

    @NotBlank
    String videoId,

    @Min(value = 0)
    @Max(value = 10)
    Integer rating,

    String text,

    @NotNull
    Long publishedAt,

    @Size(min = 1)
    Set<UUID> categoryIdList

) {}
