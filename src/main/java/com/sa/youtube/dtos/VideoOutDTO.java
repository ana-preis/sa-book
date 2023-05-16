package com.sa.youtube.dtos;

import java.util.List;
import java.util.Set;

import com.sa.youtube.models.Video;


public record VideoOutDTO(

    String id,
    String title,
    String embedHtml,
    String thumbnailUrl,
    String description,
    Set<String> tags,
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
            video.getTags(),
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
            video.getTags(),
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
            dto.tags(),
            dto.publishedAt(),
            dto.channelTitle(),
            dto.likeCount(),
            dto.viewCount(),
            null,
            null,
            null
        );
    }

    public static List<VideoOutDTO> toVideoDTOList(List<Video> videoList) {
        return videoList.stream().map(VideoOutDTO::new).toList();
    }

}