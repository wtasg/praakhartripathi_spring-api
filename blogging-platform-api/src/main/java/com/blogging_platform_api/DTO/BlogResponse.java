package com.blogging_platform_api.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class BlogResponse {
    private Long id;
    private String title;
    private String content;
    private String authorEmail;
    private List<String> categories;
    private LocalDateTime createdAt;

    public BlogResponse() {
    }

    public BlogResponse(Long id, String title, String content, String authorEmail, List<String> categories, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
        this.categories = categories;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
