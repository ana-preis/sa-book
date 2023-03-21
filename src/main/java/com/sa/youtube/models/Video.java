package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Video {
    @Id
    private String id;
    private String title;
    private String url;
    private String description;
    @ElementCollection
    private List<String> tags;
    private LocalDateTime publishedAt;
    @OneToMany
    private List<Review> reviewList;
    private String channelID;
    private Long dislikeCount;
    private Long likeCount;
}
