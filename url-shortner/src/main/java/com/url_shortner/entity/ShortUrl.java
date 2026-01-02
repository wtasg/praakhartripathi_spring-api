package com.url_shortner.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "short_urls")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "original_url", nullable = false)
    private String originalUrl;
    @Column(nullable = false, unique = true, length = 10)
    private String code;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Column(name = "click_count")
    private Long clickCount = 0L;

    public ShortUrl() {
    }

    public ShortUrl(Long id, String originalUrl, String code, LocalDateTime createdAt, LocalDateTime expiresAt, Long clickCount) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.code = code;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.clickCount = clickCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.clickCount == null) {
            this.clickCount = 0L;
        }
    }
}
