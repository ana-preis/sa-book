package com.sa.youtube.controllers;

import com.google.api.services.youtube.model.VideoListResponse;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.dtos.VideoDetailsDTO;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.VideoRepository;
import com.sa.youtube.services.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

// id exemplo "6EI1K4qP8YI"

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private YoutubeClient youtubeClient;

    @Autowired
    private VideoRepository repository;

    @Autowired
    private VideoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable String id) throws GeneralSecurityException, IOException {
        try {
            VideoDetailsDTO response = service.getVideoDetails(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            VideoListResponse response = youtubeClient.getVideo(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
    }

    @GetMapping
    public ResponseEntity<List<Video>> searchVideos(@RequestParam(defaultValue = "") String text) {
        List<Video> videoList = repository.findAllByTitleContainingIgnoreCase(text);
        return new ResponseEntity<>(videoList, HttpStatus.OK);
    }
}
