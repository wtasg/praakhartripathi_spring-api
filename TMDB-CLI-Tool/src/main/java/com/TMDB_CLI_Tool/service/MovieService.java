package com.TMDB_CLI_Tool.service;

import com.TMDB_CLI_Tool.dto.OmdbSearchResponse;

public interface MovieService {
    OmdbSearchResponse searchMovie(String query);
}
