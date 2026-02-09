package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.CommentResponse;
import com.blogging_platform_api.DTO.CreateCommentRequest;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(Long blogId, CreateCommentRequest request, String userEmail);

    List<CommentResponse> getCommentsByBlogId(Long blogId);

    void deleteComment(Long blogId, Long commentId, String userEmail);
}
