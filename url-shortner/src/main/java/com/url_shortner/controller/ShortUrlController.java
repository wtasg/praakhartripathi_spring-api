package com.url_shortner.controller;

import com.url_shortner.dto.CreateShortUrlRequest;
import com.url_shortner.dto.CreateShortUrlResponse;
import com.url_shortner.dto.GetOriginalUrlResponse;
import com.url_shortner.service.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("/api/v2/urls/shorturl")
    public ResponseEntity<CreateShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request) {
        CreateShortUrlResponse response = shortUrlService.createShortUrl(request.getOriginalUrl());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/u/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = shortUrlService.getOriginalUrl(shortCode);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/api/v1/urls/{shortCode}")
    public ResponseEntity<GetOriginalUrlResponse> getOriginalUrlResponse(@PathVariable String shortCode) {
        GetOriginalUrlResponse response = shortUrlService.getOriginalUrlDetails(shortCode);
        return ResponseEntity.ok(response);
    }
}
