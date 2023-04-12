package com.sa.youtube.dtos;

import com.sa.youtube.models.Review;

import java.util.UUID;

public record ReviewDTO(
        Float rating,
        String videoID,
        String text,
        UUID userID) {
    public ReviewDTO(Review review) {
        this (
                review.getRating(),
                review.getVideo().getId(),
                review.getText(),
                review.getUser().getId()
        );
    }
}
