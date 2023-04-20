package com.sa.youtube.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.sa.youtube.dtos.VideoDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Video {
    @Id
    private String id;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> reviewList = new ArrayList<>();
    @ManyToMany(mappedBy = "videoList")
    private List<Category> categoryList = new ArrayList<>();
    private String title;
    private String embedHtml;
    @Column(length = 1028)
    private String description;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    private LocalDateTime publishedAt;
    private String channelName;
    private Long likeCount;
    private Long viewCount;
    @ManyToMany(mappedBy = "toWatchList")
    private List<User> userToWatch;
    @ManyToMany(mappedBy = "finishedList")
    private List<User> userFinishedList;
    @ManyToMany(mappedBy = "videoList")
    private List<Playlist> playlist;

    public Video(VideoDTO dto) {
        this.id = dto.id();
        this.title = dto.title();
        this.embedHtml = dto.embedHtml();
        this.description = dto.description();
        this.tags = dto.tags();
        this.publishedAt = dto.publishedAt();
        this.channelName = dto.channelName();
        this.likeCount = dto.likeCount();
        this.viewCount = dto.viewCount();
    }
}
