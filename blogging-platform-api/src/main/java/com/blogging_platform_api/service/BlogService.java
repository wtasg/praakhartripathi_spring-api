package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.CreateBlogRequest;

public interface BlogService {
    BlogResponse createBlog(CreateBlogRequest request, String authorEmail);
}
