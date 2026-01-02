package com.url_shortner.controller;

import com.url_shortner.dto.CreateShortUrlRequest;
import com.url_shortner.dto.CreateShortUrlResponse;
import com.url_shortner.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/urls")
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("/shorturl")
    public ResponseEntity<CreateShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request) {
        CreateShortUrlResponse response = shortUrlService.createShortUrl(request.getOriginalUrl());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
