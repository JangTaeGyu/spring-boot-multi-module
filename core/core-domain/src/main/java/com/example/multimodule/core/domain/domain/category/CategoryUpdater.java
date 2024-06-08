package com.example.multimodule.core.domain.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryUpdater {
    private final CategoryRepository categoryRepository;

    public void updateCategory(Long categoryId, CategoryData data) {
        categoryRepository.updateById(categoryId, data.toDomain());
    }

    public void changeCategoryShow(Long categoryId, boolean show) {
        categoryRepository.setShow(categoryId, show);
    }
}
