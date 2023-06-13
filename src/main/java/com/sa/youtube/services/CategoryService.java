package com.sa.youtube.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.sa.youtube.models.Video;
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
        return populateWithVideo(category);
    }

    public List<CategoryDTO> search(String text) {
        if(text.isEmpty()) {
            List<Category> categories = categoryRepository.findAll();
            return ToCategoryDTOList(categories);
        }
        return ToCategoryDTOList(categoryRepository.findByNameContaining(text));
    }

    public List<CategorySimpleDTO> getCategoryList() {
        return categoryRepository
            .findAll()
            .stream()
            .map(CategorySimpleDTO::new)
            .toList();
    }

    public CategoryDTO populateWithVideo(Category category) {
        Optional<Set<Video>> videoListOpt =  videoRepository.getVideoListByCategoryId(category.getId());
        if(videoListOpt.isEmpty() || videoListOpt.get().isEmpty()) return new CategoryDTO(category);
        List<VideoOutDTO> videoDTOList = videoListOpt.get().stream().map(VideoOutDTO::new).toList();
        return new CategoryDTO(category, videoDTOList);
    }

    public List<CategoryDTO> ToCategoryDTOList(List<Category> categories) {
        return categories.stream().map(this::populateWithVideo).toList();
    }

}
