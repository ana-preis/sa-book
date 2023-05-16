package com.sa.youtube.services;

import java.util.List;
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
    public void updateCategory(UUID categoryID, Video video) {
    }
}
