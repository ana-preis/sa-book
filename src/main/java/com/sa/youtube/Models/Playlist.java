package com.sa.youtube.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private ArrayList<Video> videoList;
    private LocalDateTime publishedAt;
    private String channelID;
    private String title;
    private String description;
    @ElementCollection
    private ArrayList<String> tags;
    private String url;
}
