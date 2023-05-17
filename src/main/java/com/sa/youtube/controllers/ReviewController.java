package com.sa.youtube.controllers;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.dtos.ReviewOutDTO;
import com.sa.youtube.dtos.ReviewInDTO;
import com.sa.youtube.models.ReviewKey;
import com.sa.youtube.services.ReviewService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewOutDTO> create(@RequestBody @Valid ReviewInDTO dto)
        throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            ReviewOutDTO newReview = service.createReview(dto);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewOutDTO>> search(@RequestParam(defaultValue = "") String videoId) {
        List<ReviewOutDTO> reviews = service.search(videoId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewOutDTO> retrieve(@PathVariable ReviewKey id) {
        ReviewOutDTO review = service.getById(id);
        return new ResponseEntity<ReviewOutDTO>(review, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewOutDTO> update(@PathVariable UUID id) {
        ReviewOutDTO dto = new ReviewOutDTO(null);
        return new ResponseEntity<ReviewOutDTO>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {

    }

}
