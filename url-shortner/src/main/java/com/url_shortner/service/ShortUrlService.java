package com.url_shortner.service;

import com.url_shortner.dto.*;

public interface ShortUrlService {
    CreateShortUrlResponse createShortUrl(CreateShortUrlRequest request);
    String getOriginalUrl(String code);
    GetOriginalUrlResponse getOriginalUrlDetails(String code);
    UrlDetailsResponse getUrlDetails(String code);
    void deleteShortUrl(String code);
    UrlAnalyticsResponse getUrlAnalytics(String code);
}
