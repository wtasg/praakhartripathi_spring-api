package com.url_shortner.service;

import com.url_shortner.dto.CreateShortUrlResponse;
import com.url_shortner.entity.ShortUrl;
import com.url_shortner.repository.ShortUrlRepository;
import com.url_shortner.util.UrlCodeGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlServiceImpl implements ShortUrlService{
    private final ShortUrlRepository shortUrlRepository;
    private final UrlCodeGenerator urlCodeGenerator;

    @Value("$app.base-url")
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
                baseUrl + "/" + code,
                code
        );
    }
}
