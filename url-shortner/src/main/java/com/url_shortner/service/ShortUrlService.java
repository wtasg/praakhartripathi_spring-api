package com.url_shortner.service;

import com.url_shortner.dto.CreateShortUrlResponse;
import com.url_shortner.dto.GetOriginalUrlResponse;
import com.url_shortner.dto.UrlDetailsResponse;

public interface ShortUrlService {
    CreateShortUrlResponse createShortUrl(String original);
    String getOriginalUrl(String code);
    GetOriginalUrlResponse getOriginalUrlDetails(String code);
    UrlDetailsResponse getUrlDetails(String code);
}
