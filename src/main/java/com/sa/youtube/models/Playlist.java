package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Video> videoList = new ArrayList<>();
    private LocalDateTime publishedAt;
    private String channelID;
    private String title;
    private String description;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    private String url;
}
