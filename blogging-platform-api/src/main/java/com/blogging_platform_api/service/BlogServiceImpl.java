package com.blogging_platform_api.service;

import com.blogging_platform_api.DTO.BlogResponse;
import com.blogging_platform_api.DTO.CreateBlogRequest;
import com.blogging_platform_api.DTO.PagedResponse;
import com.blogging_platform_api.entity.Blog;
import com.blogging_platform_api.entity.Category;
import com.blogging_platform_api.entity.User;
import com.blogging_platform_api.repository.BlogRepository;
import com.blogging_platform_api.repository.CategoryRepository;
import com.blogging_platform_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public BlogServiceImpl(BlogRepository blogRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BlogResponse createBlog(CreateBlogRequest request, String authorEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(authorEmail);

        User author;
        if (optionalUser.isPresent()) {
            author = optionalUser.get();
        } else {
            throw new RuntimeException("User not found");
        }

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(request.getCategoryIds()));

//        if (categories.size() != request.getCategoryIds().size()) {
//            throw new RuntimeException("One or more categories not found");
//        }

        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setAuthor(author);
        blog.setCategories(categories);

        Blog savedBlog = blogRepository.save(blog);

        BlogResponse response = new BlogResponse();
        response.setId(savedBlog.getId());
        response.setTitle(savedBlog.getTitle());
        response.setContent(savedBlog.getContent());
        response.setAuthorEmail(author.getEmail());

        List<String> categoryNames = new ArrayList<>();
        for (Category category : savedBlog.getCategories()) {
            categoryNames.add(category.getName());
        }
        response.setCategories(categoryNames);

        response.setCreatedAt(savedBlog.getCreatedAt());

        return response;
    }

    @Override
    public PagedResponse<BlogResponse> getAllBlogs(int page, int size, String sort) {
        String[] sortParams = sort.split(",");

        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

        Page<Blog> blogPage = blogRepository.findAll(pageable);
        List<BlogResponse> blogs = new ArrayList<>();

        for (Blog blog : blogPage.getContent()) {
            BlogResponse response= new BlogResponse();
            response.setId(blog.getId());
            response.setTitle(blog.getTitle());
            response.setContent(blog.getContent());
            response.setAuthorEmail(blog.getAuthor().getEmail());

            List<String> categoryNames = new ArrayList<>();
            for (Category category : blog.getCategories()) {
                categoryNames.add(category.getName());
            }
            response.setCategories(categoryNames);
            response.setCreatedAt(blog.getCreatedAt());
            blogs.add(response);
        }

        PagedResponse<BlogResponse> response = new PagedResponse<>();
        response.setContent(blogs);
        response.setPage(blogPage.getNumber());
        response.setSize(blogPage.getSize());
        response.setTotalElements(blogPage.getTotalElements());
        response.setTotalPages(blogPage.getTotalPages());
        response.setLast(blogPage.isLast());

        return response;
    }
}
