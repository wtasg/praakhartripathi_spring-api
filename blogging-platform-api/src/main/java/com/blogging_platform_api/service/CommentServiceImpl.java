package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.CommentResponse;
import com.blogging_platform_api.DTO.CreateCommentRequest;
import com.blogging_platform_api.entity.Blog;
import com.blogging_platform_api.entity.Comment;
import com.blogging_platform_api.entity.User;
import com.blogging_platform_api.repository.BlogRepository;
import com.blogging_platform_api.repository.CommentRepository;
import com.blogging_platform_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(BlogRepository blogRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public CommentResponse createComment(Long blogId, CreateCommentRequest request, String userEmail) {
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        if(optionalBlog.isEmpty()) {
            throw new RuntimeException("Blog not found with it" + blogId);
        }
        Blog blog = optionalBlog.get();

        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setBlog(blog);
        comment.setAuthor(user);

        Comment savedComment = commentRepository.save(comment);

        CommentResponse response = new CommentResponse();
        response.setId(savedComment.getId());
        response.setContent(savedComment.getContent());
        response.setAuthorEmail(user.getEmail());
        response.setCreatedAt(savedComment.getCreatedAt());

        return response;
    }

    @Override
    public List<CommentResponse> getCommentsByBlogId(Long blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new RuntimeException("Blog not found with id" + blogId);
        }
        
        List<Comment> comments = commentRepository.findByBlog_IdOrderByCreatedAtDesc(blogId);
        List<CommentResponse> responses = new ArrayList<>();
        
        for (Comment comment : comments) {
            CommentResponse response = new CommentResponse();
            response.setId(comment.getId());
            response.setContent(comment.getContent());
            response.setAuthorEmail(comment.getAuthor().getEmail());
            response.setCreatedAt(comment.getCreatedAt());

            responses.add(response);
        }
        return responses;
    }

    @Override
    public void deleteComment(Long blogId, Long commentId, String userEmail) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isEmpty()) {
            throw new RuntimeException("Blog not found with id " + blogId);
        }
        Blog blog = blogOptional.get();

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new RuntimeException("Comment not found with id " + commentId);
        }
        Comment comment = commentOptional.get();

        if (!comment.getBlog().getId().equals(blog.getId())) {
            throw new RuntimeException("Comment does not belong to this blog");
        }

        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();

        if (!comment.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("You can delete only your own comment");
        }

        commentRepository.delete(comment);
    }

}
