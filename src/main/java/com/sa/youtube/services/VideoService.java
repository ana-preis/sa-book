package com.sa.youtube.services;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.ReviewDTO;
import com.sa.youtube.dtos.VideoDTO;
import com.sa.youtube.dtos.VideoDetailsDTO;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    public VideoDTO getVideoById(String id) {
        Video video = repository.findById(id).orElseThrow();
        return new VideoDTO(video);
    }

    @Transactional
    public Video createVideo(VideoDTO dto) {
        Video video = new Video(dto);
        Video newVideo = repository.save(video);
        return newVideo;
    }

    public VideoDetailsDTO getVideoDetails(String videoID) {
        try {
            VideoDTO videoDTO = getVideoById(videoID);
            List<Review> reviews = reviewRepository.findByVideo_Id(videoID);
            List<ReviewDTO> reviewDTOList = new ArrayList<>();
            int sum = 0;
            for (Review r : reviews) {
                sum += r.getRating();
                reviewDTOList.add(new ReviewDTO(r));
            }
            Integer totalReviews = reviews.size();
            Double averageRating = (double) sum / totalReviews;
            return new VideoDetailsDTO(videoDTO, reviewDTOList, averageRating, totalReviews);
        } catch (Exception e) {
            throw e;
        }
        
    }
}
