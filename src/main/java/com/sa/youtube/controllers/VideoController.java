package com.sa.youtube.controllers;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.dtos.VideoOutDTO;
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
import java.util.UUID;

// id exemplo "6EI1K4qP8YI"

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private VideoService service;

    /*
    @PostMapping("/{id}")
    public ResponseEntity<VideoOutDTO> create(@PathVariable String id)
        throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            VideoOutDTO dto = new VideoOutDTO(service.createVideo(id));
            return new ResponseEntity<VideoOutDTO>(dto, HttpStatus.CREATED);
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<VideoOutDTO> retrieve(@PathVariable String id)
        throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            VideoOutDTO dto = service.getVideoById(id);
            return new ResponseEntity<VideoOutDTO>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Video>> searchVideos(@RequestParam(defaultValue = "") String text) {
        List<Video> videoList = repository.findAllByTitleContainingIgnoreCase(text);
        return new ResponseEntity<>(videoList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Video>> searchVideosInCategory(@RequestParam(defaultValue = "") String text,
                                                              @PathVariable String id) {
        List<Video> videoList = repository.getAllByNameInCategory(UUID.fromString(id), text);
        return new ResponseEntity<>(videoList, HttpStatus.OK);
    }
}
