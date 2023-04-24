package com.sa.youtube.controllers;

import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.dtos.ReviewVideoForm;
import com.sa.youtube.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewDTO> save(@RequestBody ReviewVideoForm form) {
        ReviewDTO newReview = service.save(form);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ReviewDTO>> search(@RequestParam(defaultValue = "") String videoId) {
        List<ReviewDTO> reviews = service.search(videoId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/:reviewId")
    public ResponseEntity<ReviewDTO> getById(@PathVariable UUID id) {
        try {
            ReviewDTO review = service.getById(id);
            return new ResponseEntity<ReviewDTO>(review, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
