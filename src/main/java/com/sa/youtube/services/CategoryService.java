package com.sa.youtube.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.CategoryDTO;
import com.sa.youtube.dtos.VideoOutDTO;
import com.sa.youtube.models.Category;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.CategoryRepository;
import com.sa.youtube.repositories.VideoRepository;

import jakarta.transaction.Transactional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoService videoService;

    public CategoryDTO getById(UUID id) {
        Category category = repository.findById(id).orElseThrow();
        List<VideoOutDTO> videoDTOList = VideoOutDTO.toVideoDTOList(category.getVideoList());
        return new CategoryDTO(category, videoDTOList);
    }

    public List<Category> getByName(String text) {
        return repository.findByNameContaining(text);
    }

    public List<Category> getAllByIds(Set<UUID> ids) {
        return ids.stream().map(id -> repository.findById(id).orElseThrow()).toList();
    }

    @Transactional
    public CategoryDTO updateCategory(UUID categoryId, String videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow();
        Category category = repository.findById(categoryId).orElseThrow();

        video.addCategory(category);
        videoRepository.save(video);

        Long viewCount = videoRepository.getViewCountByCategoryId(categoryId);
        category.setViewCount(viewCount);
        repository.save(category);

        Set<Video> videos = videoRepository.getVideoListByCategoryId(categoryId);
        return new CategoryDTO(category, VideoOutDTO.toVideoDTOList(videos));
    }

    @Transactional
    public void updateCategory(Video video, Category category) {
        if (videoService.updateVideoCategory(video, category)) {
            Long viewCount = videoRepository.getViewCountByCategoryId(category.getId());
            category.setViewCount(viewCount);
            repository.save(category);
        }

        //Set<Video> videos = videoRepository.getVideoListByCategoryId(categoryId);
        //return new CategoryDTO(category, VideoOutDTO.toVideoDTOList(videos));
    }

}
