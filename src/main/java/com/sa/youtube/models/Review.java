package com.sa.youtube.models;

import com.google.api.client.util.DateTime;
import com.sa.youtube.dtos.ReviewDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private Integer rating;
    @ManyToOne
    private Video video;
    private DateTime publishedAt;

    public Review(ReviewDTO dto, User user, Video video) {
        this.text = dto.text();
        this.user = user;
        this.rating = dto.rating();
        this.video = video;
        this.publishedAt = new DateTime(dto.publishedAt());
    }
}
