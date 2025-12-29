package com.blogging_platform_api.controller;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.CreateBlogRequest;
import com.blogging_platform_api.DTO.PagedResponse;
import com.blogging_platform_api.DTO.UpdateBlogRequest;
import com.blogging_platform_api.entity.Blog;
import com.blogging_platform_api.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllBlogs")
    public ResponseEntity<PagedResponse<BlogResponse>> getAllBlogs(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10")int size,
                                                                   @RequestParam(defaultValue = "createdAt,desc") String sort) {
        PagedResponse<BlogResponse> response = blogService.getAllBlogs(page, size, sort);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long blogId) {
        return ResponseEntity.ok(blogService.getBlogById(blogId));
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<BlogResponse> updateBlog(@PathVariable Long blogId, @Valid @RequestBody UpdateBlogRequest  request, Authentication authentication) {
        String email = authentication.getName();
        BlogResponse response = blogService.updateBlog(blogId, request, email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long blogId, Authentication authentication) {
        String email = authentication.getName();
        blogService.deleteBlog(blogId, email);
        return ResponseEntity.ok("blog deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BlogResponse>> getBlogsByUser(@PathVariable Long userId) {
        List<BlogResponse> blogs = blogService.getBlogsByUserId(userId);
        return ResponseEntity.ok(blogs);
    }

    @PostMapping("/{blogId}/like")
    public ResponseEntity<BlogResponse> likeBlog(@PathVariable Long blogId, Authentication authentication) {
        String email = authentication.getName();
        BlogResponse response = blogService.likeBlog(blogId, email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{blogId}/likes/count")
    public ResponseEntity<Long> getBlogLikeCount(@PathVariable Long blogId) {
        return ResponseEntity.ok(blogService.getBlogLikeCount(blogId));
    }

}
