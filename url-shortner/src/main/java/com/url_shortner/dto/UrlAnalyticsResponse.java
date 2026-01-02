package com.url_shortner.dto;

import java.time.LocalDateTime;

public class UrlAnalyticsResponse {
    private Long clickCount;
    private LocalDateTime lastAccessedAt;

    public UrlAnalyticsResponse(Long clickCount, LocalDateTime lastAccessedAt) {
        this.clickCount = clickCount;
        this.lastAccessedAt = lastAccessedAt;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
}
