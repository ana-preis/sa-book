package com.sa.youtube.controllers;

import com.sa.youtube.dtos.CategoryDTO;
import com.sa.youtube.models.Category;
import com.sa.youtube.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDTO> getByID(@PathVariable UUID categoryID) {
        CategoryDTO categoryDTO = service.getById(categoryID);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> search(@RequestParam(defaultValue = "") String text) {
        List<Category> categoryList = service.search(text);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

}
