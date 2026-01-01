package com.TMDB_CLI_Tool.service;

import com.TMDB_CLI_Tool.dto.OmdbSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MovieServiceImpl implements MovieService {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    public MovieServiceImpl(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${omdb.api.url}") String baseUrl,
            @Value("${omdb.api.key}") String apiKey
    ) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Override
    public OmdbSearchResponse searchMovie(String query) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", query)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }
}
