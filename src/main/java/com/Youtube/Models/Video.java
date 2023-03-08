package com.Youtube.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {
    private String id;
    private String title;
    private String url;
    private String description;
    private ArrayList<String> tags;
    private LocalDateTime publishedAt;
    private ArrayList<String> reviewList;
    private String channelID;
    private Long dislikeCount;
    private Long likeCount;
}
