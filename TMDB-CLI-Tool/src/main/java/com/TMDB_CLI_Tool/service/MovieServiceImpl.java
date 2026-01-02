package com.TMDB_CLI_Tool.service;

import com.TMDB_CLI_Tool.dto.MovieDto;
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

    // --- Movie Methods ---

    @Override
    public OmdbSearchResponse searchMovie(String query) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", query)
                .queryParam("type", "movie")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }

    @Override
    public MovieDto getMovieDetail(String imdbId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("i", imdbId)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, MovieDto.class);
    }

    @Override
    public MovieDto getMovieDetailByTitle(String title) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("t", title)
                .queryParam("type", "movie")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, MovieDto.class);
    }

    @Override
    public OmdbSearchResponse getPopularMovies() {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", "movie")
                .queryParam("type", "movie")
                .queryParam("y", "2024")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }

    @Override
    public OmdbSearchResponse getTopRatedMovies() {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", "Oscar")
                .queryParam("type", "movie")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }

    // --- TV Show Methods ---

    @Override
    public OmdbSearchResponse searchSeries(String query) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", query)
                .queryParam("type", "series")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }

    @Override
    public MovieDto getSeriesDetailByTitle(String title) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("t", title)
                .queryParam("type", "series")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, MovieDto.class);
    }

    @Override
    public OmdbSearchResponse getPopularSeries() {
        // Simulating popular series by searching for "series" in the current year
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", "series")
                .queryParam("type", "series")
                .queryParam("y", "2024")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }

    @Override
    public OmdbSearchResponse getTopRatedSeries() {
        // Simulating top rated series by searching for "Emmy" (TV awards)
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", "Emmy")
                .queryParam("type", "series")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, OmdbSearchResponse.class);
    }
}
