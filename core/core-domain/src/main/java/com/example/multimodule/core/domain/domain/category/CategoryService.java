package com.example.multimodule.core.domain.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryFinder categoryFinder;
    private final CategoryCreator categoryCreator;
    private final CategoryUpdater categoryUpdater;

    public List<Category> getSortedCategories() {
        return categoryFinder.getSortedCategories();
    }

    public Long createCategory(CategoryData data) {
        return categoryCreator.createCategory(data);
    }

    public void updateCategory(Long categoryId, CategoryData data) {
        categoryUpdater.updateCategory(categoryId, data);
    }

    public void changeCategoryShow(Long categoryId, boolean show) {
        categoryUpdater.changeCategoryShow(categoryId, show);
    }

    public void reorderCategories(CategorySortData data) {
        categoryUpdater.reorderCategories(data);
    }
}
