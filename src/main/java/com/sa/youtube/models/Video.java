package com.sa.youtube.models;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

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
    
    private String title;
    
    private String embedHtml;

    private String thumbnailUrl;
    
    @Column(length = 1028)
    private String description;
    
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    
    private DateTime publishedAt;
    
    private String channelName;
    
    private Long likeCount;
    
    private Long viewCount;

    private Double averageRating;

    @OneToMany(mappedBy = "video")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToMany(mappedBy = "videoList")
    private List<Category> categoryList = new ArrayList<>();

    @ManyToMany(mappedBy = "toWatchList")
    private List<User> userToWatch = new ArrayList<>();

    @ManyToMany(mappedBy = "finishedList")
    private List<User> userFinishedList = new ArrayList<>();


    public Video(VideoDTO dto) {
        this.id = dto.id();
        this.title = dto.title();
        this.embedHtml = dto.embedHtml();
        this.description = dto.description();
        this.tags = dto.tags();
        this.publishedAt = new DateTime(dto.publishedAt());
        this.channelName = dto.channelName();
        this.likeCount = dto.likeCount();
        this.viewCount = dto.viewCount();
    }

    public Video(com.google.api.services.youtube.model.Video video) {
        this.id = video.getId();
        this.title = video.getSnippet().getTitle();
        this.embedHtml = video.getPlayer().getEmbedHtml();
        this.thumbnailUrl = video.getSnippet().getThumbnails().getMedium().getUrl();
        this.description = video.getSnippet().getDescription();
        this.tags = video.getSnippet().getTags();
        this.publishedAt = new DateTime(video.getSnippet().getPublishedAt().getValue());
        this.channelName = video.getSnippet().getChannelTitle();
        this.likeCount = video.getStatistics().getLikeCount().longValue();
        this.viewCount = video.getStatistics().getViewCount().longValue();
    }

}