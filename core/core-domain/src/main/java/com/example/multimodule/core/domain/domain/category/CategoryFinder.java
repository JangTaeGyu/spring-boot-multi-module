package com.example.multimodule.core.domain.domain.category;

import com.example.multimodule.core.domain.support.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryFinder {
    private final CategoryRepository categoryRepository;

    public List<Category> getSortedCategories() {
        return categoryRepository.findAllSorted();
    }

    public void checkCategory(Long categoryId) {
        boolean exists = categoryRepository.existsById(categoryId);
        if (!exists) {
            throw new NotFoundException("Category", "id", categoryId);
        }
    }
}
