package com.sa.youtube.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.dtos.VideoOutDTO;
import com.sa.youtube.services.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService service;

    @GetMapping
    public ResponseEntity<List<VideoOutDTO>> getSearch(@RequestParam String q)
        throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            return new ResponseEntity<List<VideoOutDTO>>(service.search(q), HttpStatus.OK);
    }
}
