package com.example.multimodule.core.domain.domain.category;

import lombok.Getter;

import java.util.List;

@Getter
public class CategorySortData {
    private final List<Long> ids;

    public CategorySortData(List<Long> ids) {
        this.ids = ids;
    }
}
