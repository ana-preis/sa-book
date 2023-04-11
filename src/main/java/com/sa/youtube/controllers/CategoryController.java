package com.sa.youtube.controllers;

import com.sa.youtube.dtos.CategoryDTO;
import com.sa.youtube.dtos.VideoDTO;
import com.sa.youtube.models.Category;
import com.sa.youtube.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDTO> getByID(@PathVariable UUID categoryID) {
        Optional<Category> categoryOpt = repository.findById(categoryID);
        Category newCategory = categoryOpt.get();
        List<VideoDTO> videoDTOList;
        videoDTOList = VideoDTO.toVideoDTOList(newCategory.getVideoList());
        CategoryDTO categoryDTO = new CategoryDTO(newCategory, videoDTOList);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> search(@RequestParam(defaultValue = "") String text) {
        List<Category> categoryList = repository.findByNameContaining(text);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
