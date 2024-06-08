package com.example.multimodule.core.api.controller.request;

import com.example.multimodule.core.domain.domain.category.CategorySortData;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class CategorySortRequest {
    @NotEmpty
    private List<Long> ids;

    public CategorySortData toData() {
        return new CategorySortData(ids);
    }
}
