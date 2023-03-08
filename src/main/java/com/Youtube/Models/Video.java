package com.Youtube.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
