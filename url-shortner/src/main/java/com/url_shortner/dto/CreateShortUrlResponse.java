package com.url_shortner.dto;

public class CreateShortUrlResponse {
    private String shortUrl;
    private String code;

    public CreateShortUrlResponse(String shortUrl, String code) {
        this.shortUrl = shortUrl;
        this.code = code;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
