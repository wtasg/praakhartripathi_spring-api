package com.blogging_platform_api.controller;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.CreateBlogRequest;
import com.blogging_platform_api.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity<BlogResponse> createBlog(@Valid @RequestBody CreateBlogRequest request, Authentication authentication) {
        String email = authentication.getName();
        BlogResponse response = blogService.createBlog(request, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
