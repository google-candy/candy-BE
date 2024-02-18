package com.example.diary_app.controller;

import com.example.diary_app.entity.EmotionCategory;
import com.example.diary_app.service.EmotionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
@RestController
@RequestMapping("/emotionCategories")
public class EmotionCategoryController {

    @Autowired
    private EmotionCategoryService emotionCategoryService;

    @GetMapping
    public List<EmotionCategory> getAllEmotionCategories() {
        return emotionCategoryService.getAllEmotionCategories();
    }

    @DeleteMapping("/{id}")
    public void deleteEmotionCategoryById(@PathVariable Long id) {
        emotionCategoryService.deleteEmotionCategoryById(id);
    }

    @PostMapping("/update-category-image")
    public String updateEmotionCategoryImage(
            @RequestParam("categoryName") String categoryName,
            @RequestParam("image") MultipartFile imageFile) {
        return emotionCategoryService.updateEmotionCategoryImage(categoryName, imageFile);
    }
}