package com.url_shortner.service;

import com.url_shortner.dto.CreateShortUrlResponse;
import com.url_shortner.dto.GetOriginalUrlResponse;
import com.url_shortner.dto.UrlDetailsResponse;
import com.url_shortner.entity.ShortUrl;
import com.url_shortner.exception.ResourceNotFoundException;
import com.url_shortner.repository.ShortUrlRepository;
import com.url_shortner.util.UrlCodeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShortUrlServiceImpl implements ShortUrlService{
    private final ShortUrlRepository shortUrlRepository;
    private final UrlCodeGenerator urlCodeGenerator;

    @Value("${app.base-url}")
    private String baseUrl;

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository, UrlCodeGenerator urlCodeGenerator) {
        this.shortUrlRepository = shortUrlRepository;
        this.urlCodeGenerator = urlCodeGenerator;
    }

    @Override
    public CreateShortUrlResponse createShortUrl(String originalUrl) {
        String code = urlCodeGenerator.generate();

        while (shortUrlRepository.existsByCode(code)) {
            code = urlCodeGenerator.generate();
        }
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setCode(code);

        shortUrlRepository.save(shortUrl);
        return new CreateShortUrlResponse(
                baseUrl + "/u/" + code,
                code
        );
    }

    @Override
    public String getOriginalUrl(String code) {
        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findByCode(code);

        if (optionalShortUrl.isPresent()) {
            ShortUrl shortUrl = optionalShortUrl.get();

            if (shortUrl.getExpiresAt() != null && shortUrl.getExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Short URL has expired");
            }

            shortUrl.setClickCount(shortUrl.getClickCount() + 1);
            shortUrlRepository.save(shortUrl);

            return shortUrl.getOriginalUrl();
        } else {
            throw new ResourceNotFoundException("Short url not found");
        }
    }

    @Override
    public GetOriginalUrlResponse getOriginalUrlDetails(String code) {
        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findByCode(code);

        if (optionalShortUrl.isEmpty()) {
            throw new ResourceNotFoundException("Short URL not found");
        }

        ShortUrl shortUrl = optionalShortUrl.get();

        return new GetOriginalUrlResponse(
                shortUrl.getOriginalUrl(),
                shortUrl.getCode(),
                shortUrl.getCreatedAt()
        );
    }

    @Override
    public UrlDetailsResponse getUrlDetails(String code) {
        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findByCode(code);

        if (optionalShortUrl.isEmpty()) {
            throw new ResourceNotFoundException("Short URL not found");
        }

        ShortUrl shortUrl = optionalShortUrl.get();

        return new UrlDetailsResponse(
                shortUrl.getOriginalUrl(),
                shortUrl.getCreatedAt(),
                shortUrl.getExpiresAt(),
                shortUrl.getClickCount()
        );
    }

    @Override
    public void deleteShortUrl(String code) {
        Optional<ShortUrl> optionalShortUrl = shortUrlRepository.findByCode(code);

        if (optionalShortUrl.isEmpty()) {
            throw new ResourceNotFoundException("Short URL not found");
        }

        shortUrlRepository.delete(optionalShortUrl.get());
    }
}
