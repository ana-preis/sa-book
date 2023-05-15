package com.sa.youtube.dtos;

import com.sa.youtube.models.Review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public record ReviewDTO(

        @Min(value = 0)
        @Max(value = 10)
        Integer rating,

        String text,

        @NotNull
        UUID userId,

        @NotBlank
        String videoId,

        @NotNull
        Long publishedAt

    ) {

        public ReviewDTO(Review review) {
            this (
                review.getRating(),
                review.getText(),
                review.getUser().getId(),
                review.getVideo().getId(),
                review.getPublishedAt().getValue()
            );
        }

        public static List<ReviewDTO> toReviewDTOList(Set<Review> reviewList) {
            return reviewList.stream().map(ReviewDTO::new).toList();
        }

    }
