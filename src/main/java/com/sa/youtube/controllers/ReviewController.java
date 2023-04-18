package com.sa.youtube.controllers;

import com.google.api.services.youtube.model.VideoListResponse;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.dtos.ReviewVideoForm;
import com.sa.youtube.models.Message;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.User;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.UserRepository;
import com.sa.youtube.repositories.VideoRepository;
import com.sa.youtube.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<List<Review>> search(@RequestParam(defaultValue = "") String videoId) {
        List<Review> reviews = service.search(videoId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/:reviewId")
    public ResponseEntity<Review> getById(@PathVariable UUID id) {
        Review review = service.getById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}
