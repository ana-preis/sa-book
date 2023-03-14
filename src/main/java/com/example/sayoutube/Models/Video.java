package com.example.sayoutube.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String url;
    private String description;
    @ElementCollection
    private List<String> tags;
    private LocalDateTime publishedAt;
    @ElementCollection
    private List<String> reviewList;
    private String channelID;
    private Long dislikeCount;
    private Long likeCount;
}
