package com.sa.youtube.services;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.VideoDTO;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.VideoRepository;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    public VideoDTO getVideoById(String id) throws Exception {
        Optional<Video> videoOpt = repository.findById(id);
        if (videoOpt.isPresent()) {
            return new VideoDTO(videoOpt.get());
        }
        throw new Exception();
    }

    @Transactional
    public Video createVideo(VideoDTO dto) {
        Video video = new Video(dto);
        Video newVideo = repository.save(video);
        return newVideo;
    }
}
