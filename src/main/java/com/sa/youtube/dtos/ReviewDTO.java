package com.sa.youtube.dtos;

import com.sa.youtube.models.Review;

import java.util.UUID;


public record ReviewDTO(

    UUID userId,
    String videoId,
    Integer rating,
    String text,
    Long publishedAt

) {

    public ReviewDTO(Review review) {
        this (
            review.getId().getUserId(),
            review.getId().getVideoId(),
            review.getRating(),
            review.getText(),
            review.getPublishedAt().getValue()
        );
    }

}
