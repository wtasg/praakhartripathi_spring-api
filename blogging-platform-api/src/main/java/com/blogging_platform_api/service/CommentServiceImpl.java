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
}
