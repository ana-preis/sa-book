package com.Youtube.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
