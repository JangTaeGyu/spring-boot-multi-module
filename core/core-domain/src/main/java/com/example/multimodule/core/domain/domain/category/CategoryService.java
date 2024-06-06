package com.example.multimodule.core.domain.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryFinder categoryFinder;

    public List<Category> getSortedCategories() {
        return categoryFinder.getSortedCategories();
    }
}
