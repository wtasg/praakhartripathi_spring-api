package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.CategoryResponse;
import com.blogging_platform_api.DTO.CreateCategoryRequest;
import com.blogging_platform_api.entity.Category;
import com.blogging_platform_api.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {
        Category existingCategory = categoryRepository.findByNameIgnoreCase(request.getName());

        if (existingCategory != null) {
            throw new RuntimeException("Category already exists");
        }

        Category category = new Category(request.getName());
        Category saved = categoryRepository.save(category);

        CategoryResponse response = new CategoryResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setCreatedAt(saved.getCreatedAt());
        return response;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAllByOrderByCreatedAtDesc();
        List<CategoryResponse> responses = new ArrayList<>();

        for (Category category : categories) {
            CategoryResponse response = new CategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            response.setCreatedAt(category.getCreatedAt());
            responses.add(response);
        }

        return responses;
    }
}
