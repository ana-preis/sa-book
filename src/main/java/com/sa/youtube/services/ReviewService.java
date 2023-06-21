package com.sa.youtube.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.dtos.ReviewOutDTO;
import com.sa.youtube.dtos.ReviewInDTO;
import com.sa.youtube.dtos.VideoInDTO;
import com.sa.youtube.models.Category;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.ReviewKey;
import com.sa.youtube.models.User;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.CategoryRepository;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.UserRepository;
import com.sa.youtube.repositories.VideoRepository;

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
    private YoutubeClient youtubeClient;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    
    @Transactional
    public ReviewOutDTO createReview(ReviewInDTO dto) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        
        User user = userRepository.findById(dto.userId()).orElseThrow();
        
        Video video = videoRepository.existsById(dto.videoId()) ?
            videoRepository.findById(dto.videoId()).orElseThrow() :
            createVideo(dto.videoId());
        if(dto.categoryIdList().size() > 0) dto.categoryIdList().forEach(id -> updateVideoCategory(video, id));

        Review review = reviewRepository.save(new Review(dto, user, video));
        updateVideoReviews(video);

        return new ReviewOutDTO(review);
    }
    
    public ReviewOutDTO getById(ReviewKey id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        return new ReviewOutDTO(review);
    }
    
    public List<ReviewOutDTO> search(String videoId) {
        List<Review> reviews;
        if (videoId.equals("")){
            reviews = reviewRepository.findAll();
        } else {
            reviews = reviewRepository.findByVideo_Id(videoId);
        }
        Set<Review> set = new HashSet<>(reviews);
        return toReviewDTOList(set);
    }
    
    @Transactional
    public Video createVideo(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        VideoInDTO dto = youtubeClient.getVideoInDTO(id);
        Video video = new Video(dto);
        return videoRepository.save(video);
    }

    @Transactional
    public ReviewOutDTO updateReview(ReviewInDTO dto) {
        Review review = reviewRepository.findByUserIdVideoId(dto.userId(), dto.videoId());
        review.setRating(dto.rating());
        review.setText(dto.text());
        Video video = videoRepository.findById(dto.videoId()).orElseThrow();
        updateVideoReviews(video);
        return new ReviewOutDTO(reviewRepository.save(review));
    }

    @Transactional
    public void updateVideoReviews(Video video) {
        Long reviewCount = reviewRepository.getReviewCount(video.getId());
        video.setReviewCount(reviewCount);
        Double averageRating = reviewRepository.getAverageRating(video.getId());
        video.setAverageRating(averageRating);
        videoRepository.save(video);
    }

    @Transactional
    public void updateVideoCategory(Video video, UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        if (categoryRepository.checkCategoryFromIds(video.getId(), categoryId).isEmpty()) {
            video.addCategory(category);
            videoRepository.save(video);
            Long viewCount = videoRepository.getViewCountByCategoryId(category.getId());
            category.setViewCount(viewCount);
            categoryRepository.save(category);
        }
    }

    public List<ReviewOutDTO> toReviewDTOList(Set<Review> reviews) {
        return reviews.stream().map(ReviewOutDTO::new).toList();
    }

}
