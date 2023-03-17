package com.sa.youtube.controllers;

import com.sa.youtube.models.Video;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {

    @GetMapping
    public ResponseEntity<Video> getByID(@RequestParam UUID id) {
    Video video = new Video();
        video.setId(id);
        video.setUrl("www.url");
        video.setTitle("Test");
    return ResponseEntity.ok(video);
    }
}
