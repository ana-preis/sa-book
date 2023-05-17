package com.sa.youtube.dtos;

import java.util.List;

import com.sa.youtube.models.Video;


public record VideoOutDTO(

    String id,
    String title,
    String embedHtml,
    String thumbnailUrl,
    String description,
    Long publishedAt,
    String channelTitle,
    Long likeCount,
    Long viewCount,
    Long reviewCount,
    Double averageRating,
    List<ReviewDTO> reviews

) {

    public VideoOutDTO(Video video, List<ReviewDTO> reviews) {
        this(
            video.getId(),
            video.getTitle(),
            video.getEmbedHtml(),
            video.getThumbnailUrl(),
            video.getDescription(),
            video.getPublishedAt().getValue(),
            video.getChannelTitle(),
            video.getLikeCount(),
            video.getViewCount(),
            video.getReviewCount(),
            video.getAverageRating(),
            reviews
        );
    }

    public VideoOutDTO(Video video) {
        this(
            video.getId(),
            video.getTitle(),
            video.getEmbedHtml(),
            video.getThumbnailUrl(),
            video.getDescription(),
            video.getPublishedAt().getValue(),
            video.getChannelTitle(),
            video.getLikeCount(),
            video.getViewCount(),
            video.getReviewCount(),
            video.getAverageRating(),
            null
        );
    }

    public VideoOutDTO(VideoInDTO dto) {
        this(
            dto.id(),
            dto.title(),
            dto.embedHtml(),
            dto.thumbnailUrl(),
            dto.description(),
            dto.publishedAt(),
            dto.channelTitle(),
            dto.likeCount(),
            dto.viewCount(),
            null,
            null,
            null
        );
    }

}