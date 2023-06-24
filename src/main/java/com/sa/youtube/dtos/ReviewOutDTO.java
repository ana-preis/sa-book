package com.sa.youtube.dtos;

import com.sa.youtube.models.Review;
import com.sa.youtube.models.User;

import java.util.UUID;


public record ReviewOutDTO(

    UUID userId,
    String username,
    String videoId,
    Integer rating,
    String text,
    Long publishedAt

) {

    public ReviewOutDTO(Review review) {
        this (
            review.getId().getUserId(),
            null,
            review.getId().getVideoId(),
            review.getRating(),
            review.getText(),
            review.getPublishedAt().getValue()
        );
    }

    public ReviewOutDTO(Review review, User user) {
        this (
            review.getId().getUserId(),
            user.getName(),
            review.getId().getVideoId(),
            review.getRating(),
            review.getText(),
            review.getPublishedAt().getValue()
        );
    }

}
