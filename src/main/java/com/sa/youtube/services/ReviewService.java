package com.sa.youtube.services;

import com.google.api.services.youtube.model.VideoListResponse;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.dtos.ReviewVideoForm;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.User;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.UserRepository;
import com.sa.youtube.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private YoutubeClient youtubeClient;

    @Autowired
    private VideoRepository videoRepository;

    public Review save(ReviewVideoForm form) throws GeneralSecurityException, IOException {
        Video video = new Video();
        video.setId(form.video().id());
        video.setUrl(form.video().url());
        video.setChannelID(form.video().channelID());
        video.setTitle(form.video().title());
        Video newVideo = videoRepository.save(video);
        Review review = new Review();
        Optional<User> userOpt = userRepository.findById(form.review().userID());
        System.out.println(userOpt.get());

        if(userOpt.isPresent()) {
            review.setVideo(newVideo);
            review.setRating(form.review().rating());
            review.setUser(userOpt.get());
            System.out.println(review);
            Review newReview = repository.save(review);
            return newReview;
        }
        return review;
    }

    public List<Review> getByVideoId(String id) {
        System.out.println(id);
        List<Review> reviewsOpt = repository.findByVideo_Id(id);
        System.out.println("REVIEWS OPTIONAL: " + reviewsOpt);
//        if (reviewsOpt.isPresent()) {
//            return reviewsOpt.get();
//        }
        return reviewsOpt;
    }

    public Review getById(UUID id) {
        Optional<Review> reviewOpt = repository.findById(id);
        if(reviewOpt.isPresent()) {
            return reviewOpt.get();
        }
        return new Review();
    }
}
