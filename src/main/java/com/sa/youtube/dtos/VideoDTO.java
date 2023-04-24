package com.sa.youtube.dtos;


import com.sa.youtube.models.Review;
import com.sa.youtube.models.Video;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record VideoDTO(
        String id,
        String title,
        String embedHtml,
        String description,
        List<String> tags,
        Long publishedAt,
        List<Review> reviewList,
        String channelName,
        Long likeCount,
        Long viewCount
    ) {

    public VideoDTO(Video video) {
        this(
            video.getId(),
            video.getTitle(),
            video.getEmbedHtml(),
            video.getDescription(),
            video.getTags(),
            video.getPublishedAt().getValue(),
            video.getReviewList(),
            video.getChannelName(),
            video.getLikeCount(),
            video.getViewCount()
        );
    }

    public static List<VideoDTO> toVideoDTOList(List<Video> videoList) {
        return videoList.stream()
            .map(video -> new VideoDTO(video))
            .collect(Collectors.toList());
    }
}
