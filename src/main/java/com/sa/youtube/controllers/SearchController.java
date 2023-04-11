package com.sa.youtube.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sa.youtube.clients.YoutubeClient;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private YoutubeClient youtubeClient;

    @GetMapping
    public ResponseEntity<String> getSearch(@RequestParam String q, @RequestParam String type)
        throws GeneralSecurityException, IOException {
        youtubeClient.getSearchList(q, type);
        return ResponseEntity.ok("ok");
    }
}
