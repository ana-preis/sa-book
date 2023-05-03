package com.sa.youtube.dtos;

import com.sa.youtube.models.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ReviewDTO(

        @Size(min = 0, max = 10)
        Integer rating,

        String text,

        @NotBlank
        UUID userID,

        @NotNull
        Long publishedAt

    ) {

        public ReviewDTO(Review review) {
            this (
                    review.getRating(),
                    review.getText(),
                    review.getUser().getId(),
                    review.getPublishedAt().getValue()
            );
        }

        public static List<ReviewDTO> toReviewDTOList(List<Review> reviewList) {
            return reviewList.stream()
                .map(review -> new ReviewDTO(review))
                .collect(Collectors.toList());
        }

    }
