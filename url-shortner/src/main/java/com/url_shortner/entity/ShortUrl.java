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

    public ShortUrl() {
    }

    public ShortUrl(Long id, String originalUrl, String code, LocalDateTime createdAt) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.code = code;
        this.createdAt = createdAt;
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

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
