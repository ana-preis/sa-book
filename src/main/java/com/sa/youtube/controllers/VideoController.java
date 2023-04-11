package com.sa.youtube.controllers;

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
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private YoutubeClient youtubeClient;

    @Autowired
    private VideoRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Video> getByID(@PathVariable String id) throws GeneralSecurityException, IOException {
        Video video = new Video();
        video.setId(id);
        video.setUrl("www.url");
        video.setTitle("Test");
        youtubeClient.getVideoList();
        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Video>> searchVideos(@RequestParam(defaultValue = "") String text) {
        List<Video> videoList = repository.findAllByTitleContainingIgnoreCase(text);
        return new ResponseEntity<>(videoList, HttpStatus.OK);
    }
}
