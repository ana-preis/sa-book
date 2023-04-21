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
import java.util.Optional;
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
        Video newVideo = videoService.createVideo(form.video());
        Optional<User> userOpt = userRepository.findById(form.review().userID());
        System.out.println(userOpt.get().getName());

        if(userOpt.isPresent()) {
            Review review = new Review(form.review(), userOpt.get(), newVideo);
            Review newReview = repository.save(review);
            return new ReviewDTO(newReview) ;
        }
        Review emptyReview = new Review();
        return new ReviewDTO(emptyReview);
    }

    public ReviewDTO getById(UUID id) throws Exception {
        Optional<Review> reviewOpt = repository.findById(id);
        if(reviewOpt.isPresent()) {
            return new ReviewDTO(reviewOpt.get());
        }
        throw new Exception();
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
