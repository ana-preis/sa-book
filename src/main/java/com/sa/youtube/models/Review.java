package com.sa.youtube.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.DateTime;
import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.dtos.VideoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

//@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Review{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String text;
    @ManyToOne
    private User user;
    private Float rating;
    @ManyToOne
    private Video video;
    private DateTime publishedAt;
    private Boolean validateRating(Float rating) {
        return rating >=0 && rating <= 10;
    }

    public Review(ReviewDTO dto, User user, Video video) {
        this.text = dto.text();
        this.user = user;
        this.rating = dto.rating();
        this.video = video;
        this.publishedAt = new DateTime(dto.publishedAt());
    }
}
