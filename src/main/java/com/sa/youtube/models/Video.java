package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Video {
    @Id
    private String id;
    @OneToMany
    private List<Review> reviewList = new ArrayList<>();
    @ManyToMany(mappedBy = "videoList")
    private List<Category> categoryList = new ArrayList<>();
    private String title;
    private String url;
    private String description;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    private LocalDateTime publishedAt;
     private String channelID;
    private String channelName;
    private Long dislikeCount;
    private Long likeCount;
    private Long viewCount;
}
