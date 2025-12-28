package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.CommentResponse;
import com.blogging_platform_api.DTO.CreateCommentRequest;

public interface CommentService {
    CommentResponse createComment(Long blogId, CreateCommentRequest request, String userEmail);
}
