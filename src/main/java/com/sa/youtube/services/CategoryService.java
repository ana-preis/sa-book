package com.sa.youtube.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.CategoryDTO;
import com.sa.youtube.dtos.CategorySimpleDTO;
import com.sa.youtube.dtos.VideoOutDTO;
import com.sa.youtube.models.Category;
import com.sa.youtube.repositories.CategoryRepository;
import com.sa.youtube.repositories.VideoRepository;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VideoRepository videoRepository;

    public CategoryDTO getById(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        List<VideoOutDTO> videoDTOList = videoRepository.getVideoListByCategoryId(id)
            .stream().map(VideoOutDTO::new).toList();
        return new CategoryDTO(category, videoDTOList);
    }

    public List<Category> search(String text) {
        if(text.isEmpty()) {
            return categoryRepository.findAll();
        }
        return categoryRepository.findByNameContaining(text);
    }

    public List<CategorySimpleDTO> getCategoryList() {
        return categoryRepository
            .findAll()
            .stream()
            .map(CategorySimpleDTO::new)
            .toList();
    }

}
