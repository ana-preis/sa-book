package com.sa.youtube.dtos;

import com.sa.youtube.models.Review;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ReviewDTO(
        Float rating,
        String text,
        UUID userID
    ) {

    public ReviewDTO(Review review) {
        this (
            review.getRating(),
            review.getText(),
            review.getUser().getId()
        );
    }

    public static List<ReviewDTO> toReviewDTOList(List<Review> reviewList) {
        return reviewList.stream()
            .map(review -> new ReviewDTO(review))
            .collect(Collectors.toList());
    }
}
