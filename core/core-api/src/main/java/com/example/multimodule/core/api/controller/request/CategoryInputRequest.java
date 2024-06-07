package com.example.multimodule.core.api.controller.request;

import com.example.multimodule.core.domain.domain.category.CategoryData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CategoryInputRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public CategoryData toData() {
        return new CategoryData(name, description);
    }
}
