package com.blogging_platform_api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class UpdateBlogRequest {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "content is requiried")
    private String content;
    @NotEmpty(message = "atleast one category is required")
    private List<Long> categoryIds;

    public UpdateBlogRequest() {
    }

    public UpdateBlogRequest(String title, String content, List<Long> categoryIds) {
        this.title = title;
        this.content = content;
        this.categoryIds = categoryIds;
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

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
