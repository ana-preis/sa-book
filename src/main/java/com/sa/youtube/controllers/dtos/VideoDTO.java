package com.sa.youtube.controllers.dtos;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VideoDTO(
        UUID id,
        String title,
        String url,
        String description,
        List<String> tags,
        LocalDateTime publishedAt,
        List<String> reviewList,
        String channelID,
        Long dislikeCount,
        Long likeCount) {

}
