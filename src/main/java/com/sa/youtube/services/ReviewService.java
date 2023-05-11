package com.sa.youtube.services;

import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.dtos.ReviewVideoForm;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.User;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoService videoService;

    @Transactional
    public ReviewDTO save(ReviewVideoForm form) {
        User user = userRepository.findById(form.review().userID()).orElseThrow();
        Video newVideo = videoService.createVideo(form.video());
        Review newReview = new Review(form.review(), user, newVideo);
        return new ReviewDTO(repository.save(newReview));
    }

    public ReviewDTO getById(UUID id) {
        Review review = repository.findById(id).orElseThrow();
        return new ReviewDTO(review);
    }

    public List<ReviewDTO> search(String videoId) {
        List<Review> reviews;
        if (videoId.equals("")){
            reviews = repository.findAll();
        } else {
            reviews = repository.findByVideo_Id(videoId);
        }
        return ReviewDTO.toReviewDTOList(reviews);
    }

}
