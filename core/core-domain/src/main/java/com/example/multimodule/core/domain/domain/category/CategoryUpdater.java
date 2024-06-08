package com.example.multimodule.core.domain.domain.category;

import com.example.multimodule.core.domain.support.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private void checkCategoryIds(List<Long> ids) {
        Long count = categoryRepository.getCountByIds(ids);
        if (count != ids.size()) {
            throw new NotFoundException("Category", "ids", ids);
        }
    }

    public void reorderCategories(CategorySortData data) {
        checkCategoryIds(data.getIds());
        categoryRepository.updateCategorySort(data.getIds());
    }
}
