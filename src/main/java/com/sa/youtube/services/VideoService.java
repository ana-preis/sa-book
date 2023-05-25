package com.sa.youtube.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.sa.youtube.clients.YoutubeClient;
import com.sa.youtube.dtos.VideoOutDTO;
import com.sa.youtube.dtos.ReviewOutDTO;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.ReviewRepository;
import com.sa.youtube.repositories.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private YoutubeClient youtubeClient;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private VideoRepository videoRepository;

    public VideoOutDTO getVideoById(String id) throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        if (videoRepository.existsById(id)) {
            Video video = videoRepository.findById(id).orElseThrow();
            List<ReviewOutDTO> reviews = reviewRepository.getReviewDTOsByVideoId(id);
            return new VideoOutDTO(video, reviews, null);
        }
        return new VideoOutDTO(youtubeClient.getVideoInDTO(id));
    }

}
