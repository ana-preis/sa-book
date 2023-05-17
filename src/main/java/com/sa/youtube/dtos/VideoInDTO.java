package com.sa.youtube.dtos;

import com.google.api.services.youtube.model.Video;


public record VideoInDTO(

    String id,
    String title,
    String embedHtml,
    String thumbnailUrl,
    String description,
    Long publishedAt,
    String channelTitle,
    Long likeCount,
    Long viewCount
    
) {

    public VideoInDTO(Video video) {
        this(
            video.getId(),
            video.getSnippet().getTitle(),
            video.getPlayer().getEmbedHtml(),
            video.getSnippet().getThumbnails().getMedium().getUrl(),
            video.getSnippet().getDescription(),
            video.getSnippet().getPublishedAt().getValue(),
            video.getSnippet().getChannelTitle(),
            video.getStatistics().getLikeCount().longValue(),
            video.getStatistics().getViewCount().longValue()
        );
    }

}
