package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.domain.category.CategoryFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostValidation {
    private final CategoryFinder categoryFinder;

    public void validate(PostData postData) {
        categoryFinder.checkCategory(postData.getCategoryId());
    }
}
