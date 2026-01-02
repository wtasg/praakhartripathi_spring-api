package com.url_shortner.dto;

import java.time.LocalDateTime;

public class GetOriginalUrlResponse {
    private String originalUrl;
    private String code;
    private LocalDateTime createdAt;

    public GetOriginalUrlResponse(String originalUrl, String code, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.code = code;
        this.createdAt = createdAt;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
