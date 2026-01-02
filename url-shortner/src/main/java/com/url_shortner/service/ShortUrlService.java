package com.url_shortner.service;

import com.url_shortner.dto.CreateShortUrlResponse;

public interface ShortUrlService {
    CreateShortUrlResponse createShortUrl(String original);
}
