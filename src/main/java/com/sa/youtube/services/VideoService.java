package com.sa.youtube.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.dtos.VideoOutDTO;
import com.sa.youtube.dtos.VideoInDTO;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private YoutubeClient youtubeClient;

    @Autowired
    private VideoRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    public VideoOutDTO getVideoById(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        Optional<Video> opt = repository.findById(id);
        if (opt.isPresent()) {
            return new VideoOutDTO(opt.get(), reviewRepository.getReviewDTOsByVideoId(id));
        }
        return new VideoOutDTO(youtubeClient.getVideoInDTO(id));
    }

    @Transactional
    public Video getOrCreateVideo(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        return repository.findById(id).orElse(createVideo(id));
    }

    @Transactional
    public Video createVideo(VideoInDTO dto) {
        return repository.save(new Video(dto));
    }

    @Transactional
    public Video createVideo(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        VideoInDTO dto = youtubeClient.getVideoInDTO(id);
        return repository.save(new Video(dto));
    }

    @Transactional
    public void updateVideoReviews(Video video) {
        Long reviewCount = reviewRepository.getReviewCount(video.getId());
        video.setReviewCount(reviewCount);
        Double averageRating = reviewRepository.getAverageRating(video.getId());
        video.setAverageRating(averageRating);
        repository.save(video);
    }

}
