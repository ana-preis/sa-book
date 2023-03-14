package com.example.sayoutube.Controller;

import com.example.sayoutube.Models.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {

    @GetMapping
    public ResponseEntity<Video> getByID() {
    Video video = new Video();
        video.setId(UUID.randomUUID());
        video.setUrl("www.url");
        video.setTitle("Test");
    return ResponseEntity.ok(video);
    }
}
