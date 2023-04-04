package com.sa.youtube.controllers;

import com.sa.youtube.models.Category;
import com.sa.youtube.models.Video;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<Category>> searchCategories(@RequestParam(defaultValue = "") UUID id,
                                                           @RequestParam(defaultValue = "") String title) {
        List<Category> videoList = new ArrayList<>();
        return ResponseEntity.ok(videoList);
    }
}
