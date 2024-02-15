package com.example.diary_app.controller;

import com.example.diary_app.entity.EmotionCategory;
import com.example.diary_app.service.EmotionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
