package com.example.multimodule.core.domain.domain.category;

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
}
