package com.sa.youtube.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.DateTime;
import com.sa.youtube.dtos.ReviewDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Review{

    @EmbeddedId
    ReviewKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private Video video;

    private Integer rating;
    private String text;
    private DateTime publishedAt;


    public Review(ReviewDTO dto, User user, Video video) {
        this.id = new ReviewKey(dto.userId(), dto.videoId());
        this.user = user;
        this.video = video;
        this.rating = dto.rating();
        this.text = dto.text();
        this.publishedAt = new DateTime(dto.publishedAt());
    }

}