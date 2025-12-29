package com.blogging_platform_api.controller;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.CategoryResponse;
import com.blogging_platform_api.DTO.CreateCategoryRequest;
import com.blogging_platform_api.service.BlogService;
import com.blogging_platform_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final BlogService blogService;
    private final CategoryService categoryService;

    public CategoryController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}/blogs")
    public ResponseEntity<List<BlogResponse>> getBlogsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(blogService.getBlogsByCategories(categoryId));
    }
}
