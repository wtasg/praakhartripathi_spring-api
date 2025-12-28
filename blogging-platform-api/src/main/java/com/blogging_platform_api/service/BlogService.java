package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.CreateBlogRequest;
import com.blogging_platform_api.DTO.PagedResponse;
import com.blogging_platform_api.DTO.UpdateBlogRequest;

public interface BlogService {
    BlogResponse createBlog(CreateBlogRequest request, String authorEmail);
    PagedResponse<BlogResponse> getAllBlogs(int page, int size, String sort);
    BlogResponse getBlogById(Long blogId);
    BlogResponse updateBlog(Long blogId, UpdateBlogRequest request, String loggedInUserEmail);
    void deleteBlog(Long blogId, String loggedInUserEmail);
}
