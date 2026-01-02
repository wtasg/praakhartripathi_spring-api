package com.url_shortner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateShortUrlRequest {
    @NotBlank(message = "Original Url must not be blank")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Invalid URL format"
    )
    private String originalUrl;

    public CreateShortUrlRequest() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
