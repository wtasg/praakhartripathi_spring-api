package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.CreateBlogRequest;
import com.blogging_platform_api.DTO.PagedResponse;

public interface BlogService {
    BlogResponse createBlog(CreateBlogRequest request, String authorEmail);
    PagedResponse<BlogResponse> getAllBlogs(int page, int size, String sort);
}
