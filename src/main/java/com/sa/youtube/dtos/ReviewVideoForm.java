package com.sa.youtube.dtos;

public record ReviewVideoForm(
        ReviewDTO review,
        VideoDTO video
) {
}
