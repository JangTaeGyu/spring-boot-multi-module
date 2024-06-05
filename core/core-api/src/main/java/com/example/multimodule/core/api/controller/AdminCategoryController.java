package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.response.SuccessfulResponse;
import com.example.multimodule.core.domain.domain.category.Category;
import com.example.multimodule.core.domain.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> index() {
        List<Category> categories = categoryService.getSortedCategories();
        SuccessfulResponse<List<Category>> response = new SuccessfulResponse<>(categories);
        return ResponseEntity.ok(response);
    }
}
