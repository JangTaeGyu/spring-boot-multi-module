package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.request.CategoryInputRequest;
import com.example.multimodule.core.api.controller.request.CategorySortRequest;
import com.example.multimodule.core.api.controller.response.CreatedResponse;
import com.example.multimodule.core.api.controller.response.SuccessfulResponse;
import com.example.multimodule.core.domain.domain.category.Category;
import com.example.multimodule.core.domain.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<CreatedResponse> create(@RequestBody @Valid CategoryInputRequest request) {
        Long categoryId = categoryService.createCategory(request.toData());
        CreatedResponse response = new CreatedResponse(categoryId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Void> update(@PathVariable Long categoryId, @RequestBody @Valid CategoryInputRequest request) {
        categoryService.updateCategory(categoryId, request.toData());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{categoryId}/enable")
    public ResponseEntity<Void> enable(@PathVariable Long categoryId) {
        categoryService.changeCategoryShow(categoryId, true);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{categoryId}/disable")
    public ResponseEntity<Void> disable(@PathVariable Long categoryId) {
        categoryService.changeCategoryShow(categoryId, false);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/sort")
    public ResponseEntity<Void> sort(@RequestBody @Valid CategorySortRequest request) {
        categoryService.reorderCategories(request.toData());
        return ResponseEntity.ok(null);
    }
}
