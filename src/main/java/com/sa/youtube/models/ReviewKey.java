package com.sa.youtube.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewKey implements Serializable {

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "video_id")
    String videoId;

}
