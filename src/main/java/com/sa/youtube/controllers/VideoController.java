package com.sa.youtube.controllers;

import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.controllers.dtos.VideoDTO;
import com.sa.youtube.models.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private YoutubeClient youtubeClient;

    @GetMapping
    public ResponseEntity<Video> getByID(@RequestParam String id) throws GeneralSecurityException, IOException {
        Video video = new Video();
        video.setId(id);
        video.setUrl("www.url");
        video.setTitle("Test");
        youtubeClient.getVideoList();
        return ResponseEntity.ok(video);
    }

//    @GetMapping
//    public ResponseEntity<List<Video>> searchVideos(@RequestParam UUID id, @RequestParam String title) {
//        // manda id e title para camada service retornar o videoList
//        List<Video> videoList = new ArrayList<>();
//        return ResponseEntity.ok(videoList);
//    }
}
