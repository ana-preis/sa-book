package com.sa.youtube.controllers;

import com.google.api.services.youtube.model.VideoListResponse;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

// id exemplo "6EI1K4qP8YI"

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private YoutubeClient youtubeClient;

    @Autowired
    private VideoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable String id) throws GeneralSecurityException, IOException {
        Video video = new Video();
        video.setId(id);
        video.setUrl("www.url");
        video.setTitle("Test");
        VideoListResponse response = youtubeClient.getVideo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Video>> searchVideos(@RequestParam(defaultValue = "") String text) {
        List<Video> videoList = repository.findAllByTitleContainingIgnoreCase(text);
        return new ResponseEntity<>(videoList, HttpStatus.OK);
    }
}
