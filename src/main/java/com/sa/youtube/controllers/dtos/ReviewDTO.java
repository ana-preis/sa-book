package com.sa.youtube.controllers.dtos;

import java.util.UUID;

public record ReviewDTO(
        UUID id,
        Float rating,
        String videoID) {
}
