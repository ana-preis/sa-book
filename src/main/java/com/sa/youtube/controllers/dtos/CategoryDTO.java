package com.sa.youtube.controllers.dtos;

import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String title,
        String url,
        Long viewCount) {
}
