package com.sa.youtube.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record SnippetDTO(
        LocalDateTime publishedAt,
        String channelId,
        String title,
        String descriptioString,
        String channelTitle,
        List<String> tags) {
}
