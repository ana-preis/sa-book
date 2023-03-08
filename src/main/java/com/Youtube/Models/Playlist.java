package com.Youtube.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playlist {
    private String id;
    private ArrayList<Video> videoList;
    private LocalDateTime publishedAt;
    private String channelID;
    private String title;
    private String description;
    private ArrayList<String> tags;
    private String url;
}
