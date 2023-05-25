package com.sa.youtube.dtos;

import java.util.List;
import java.util.UUID;

import com.google.api.services.youtube.model.SearchResult;
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
    List<ReviewOutDTO> reviews,
    List<UUID> categoryIDList

) {

    public VideoOutDTO(Video video, List<ReviewOutDTO> reviews, List<CategorySimpleDTO> categories) {
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
            reviews,
            null
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
            null,
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
            null,
            null
        );
    }

    public VideoOutDTO(SearchResult res) {
        this(
            res.getId().getVideoId(),
            res.getSnippet().getTitle(),
            null,
            res.getSnippet().getThumbnails().getMedium().getUrl(),
            res.getSnippet().getDescription(),
            res.getSnippet().getPublishedAt().getValue(),
            res.getSnippet().getChannelTitle(),
            null,
            null,
            null,
            null,
            null,
            null
        );
    }

}