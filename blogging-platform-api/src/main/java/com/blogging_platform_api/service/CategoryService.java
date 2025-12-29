package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.CategoryResponse;
import com.blogging_platform_api.DTO.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryRequest request);
    List<CategoryResponse> getAllCategories();
}
