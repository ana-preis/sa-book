package com.sa.youtube.controllers;

import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.User;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.UserRepository;
import com.sa.youtube.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Review> save(@RequestBody @Valid ReviewDTO reviewDTO) {
        Review review = new Review();
        Optional<Video> videoOpt = videoRepository.findById(reviewDTO.videoID());
        Optional<User> userOpt = userRepository.findById(reviewDTO.userID());
        review.setVideo(videoOpt.get());
        review.setRating(reviewDTO.rating());
        review.setUser(userOpt.get());
        Review newReview = repository.save(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
}
