package com.sa.youtube.controllers;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.dtos.ReviewDTO;
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
    public ResponseEntity<ReviewDTO> create(@RequestBody @Valid ReviewInDTO dto)
        throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            ReviewDTO newReview = service.save(dto);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> search(@RequestParam(defaultValue = "") String videoId) {
        List<ReviewDTO> reviews = service.search(videoId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> retrieve(@PathVariable ReviewKey id) {
        ReviewDTO review = service.getById(id);
        return new ResponseEntity<ReviewDTO>(review, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable UUID id) {
        ReviewDTO dto = new ReviewDTO(null);
        return new ResponseEntity<ReviewDTO>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {

    }

}
