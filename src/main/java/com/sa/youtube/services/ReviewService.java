package com.sa.youtube.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.User;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoService videoService;

    public List<ReviewDTO> toReviewDTOList(Set<Review> reviews) {
        return reviews.stream().map(ReviewDTO::new).toList();
    }

    @Transactional
    public ReviewDTO save(ReviewDTO dto) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        User user = userRepository.findById(dto.userId()).orElseThrow();
        Video video = videoService.getOrCreateVideo(dto.videoId());
        Review review = repository.save(new Review(dto, user, video));
        videoService.updateVideoReviews(video, review);
        return new ReviewDTO(review);
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
        Set<Review> set = new HashSet<>(reviews);
        return toReviewDTOList(set);
    }

}
