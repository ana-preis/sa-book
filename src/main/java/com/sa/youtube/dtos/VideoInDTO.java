package com.sa.youtube.dtos;

import java.util.HashSet;
import java.util.Set;

import com.google.api.services.youtube.model.Video;

public record VideoInDTO(

    String id,
    String title,
    String embedHtml,
    String thumbnailUrl,
    String description,
    Set<String> tags,
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
            new HashSet<>(video.getSnippet().getTags()),
            video.getSnippet().getPublishedAt().getValue(),
            video.getSnippet().getChannelTitle(),
            video.getStatistics().getLikeCount().longValue(),
            video.getStatistics().getViewCount().longValue()
        );
    }

}
