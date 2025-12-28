package com.blogging_platform_api.controller;

import com.blogging_platform_api.DTO.CommentResponse;
import com.blogging_platform_api.DTO.CreateCommentRequest;
import com.blogging_platform_api.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/{blogId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long blogId, @Valid @RequestBody CreateCommentRequest request, Authentication authentication) {
        String email = authentication.getName();
        CommentResponse response = commentService.createComment(blogId, request, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{blogId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBlogsId(@PathVariable Long blogId) {
        List<CommentResponse> comments = commentService.getCommentsByBlogId(blogId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{blogId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long blogId, @PathVariable Long commentId, Authentication authentication) {
        String email = authentication.getName();
        commentService.deleteComment(blogId, commentId, email);
        return ResponseEntity.noContent().build();
    }
}
