package com.sa.youtube.models;

import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import com.sa.youtube.dtos.VideoInDTO;


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
    
    @Column(length = 5000)
    private String description;

    @ElementCollection
    private Set<String> tags = new HashSet<>();
    
    private DateTime publishedAt;
    
    private String channelTitle;
    
    private Long likeCount;
    
    private Long viewCount;

    private Double averageRating;

    @OneToMany(mappedBy = "video")
    private Set<Review> reviewList = new HashSet<>();

    @ManyToMany(mappedBy = "videoList")
    private Set<Category> categoryList = new HashSet<>();

    @ManyToMany(mappedBy = "toWatchList")
    private Set<User> userToWatch = new HashSet<>();

    @ManyToMany(mappedBy = "finishedList")
    private Set<User> userFinishedList = new HashSet<>();


    public Video(VideoInDTO dto) {
        this.id = dto.id();
        this.title = dto.title();
        this.embedHtml = dto.embedHtml();
        this.thumbnailUrl = dto.thumbnailUrl();
        this.description = dto.description();
        this.tags = dto.tags();
        this.publishedAt = new DateTime(dto.publishedAt());
        this.channelTitle = dto.channelTitle();
        this.likeCount = dto.likeCount();
        this.viewCount = dto.viewCount();
    }

}