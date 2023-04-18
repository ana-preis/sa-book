package com.sa.youtube.models;

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
    @OneToMany(mappedBy = "video")
    private List<Review> reviewList = new ArrayList<>();
    @ManyToMany(mappedBy = "videoList")
    private List<Category> categoryList = new ArrayList<>();
    private String title;
    private String embedHtml;
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
        id = dto.id();
        title = dto.title();
        embedHtml = dto.embedHtml();
        description = dto.description();
        tags = dto.tags();
        publishedAt = dto.publishedAt();
        channelName = dto.channelName();
        likeCount = dto.likeCount();
        viewCount = dto.viewCount();
    }
}
