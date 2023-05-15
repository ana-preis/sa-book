package com.sa.youtube.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.CategoryDTO;
import com.sa.youtube.dtos.VideoOutDTO;
import com.sa.youtube.models.Category;
import com.sa.youtube.models.Video;
import com.sa.youtube.repositories.CategoryRepository;

import jakarta.transaction.Transactional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO getById(UUID id) {
        Category category = repository.findById(id).orElseThrow();
        List<VideoOutDTO> videoDTOList = VideoOutDTO.toVideoDTOList(category.getVideoList());
        return new CategoryDTO(category, videoDTOList);
    }

    public List<Category> getByName(String text) {
        return repository.findByNameContaining(text);
    }

    @Transactional
    public CategoryDTO updateCategory(UUID categoryID, Video video) throws Exception {
        Optional<Category> categoryOpt = repository.findById(categoryID);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            List<Video> videoList = category.getVideoList();
            videoList.add(video);
            category.setVideoList(videoList);
            Category updatedCategory = repository.save(category);
            List<VideoOutDTO> videoDTOList = VideoOutDTO.toVideoDTOList(updatedCategory.getVideoList());
            return new CategoryDTO(updatedCategory, videoDTOList);
        }
        throw new Exception();
    }
}
