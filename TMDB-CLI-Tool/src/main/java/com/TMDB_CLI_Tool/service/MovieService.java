package com.TMDB_CLI_Tool.service;

import com.TMDB_CLI_Tool.dto.MovieDto;
import com.TMDB_CLI_Tool.dto.OmdbSearchResponse;

public interface MovieService {
    OmdbSearchResponse searchMovie(String query);
    MovieDto getMovieDetail(String imdbId);
    MovieDto getMovieDetailByTitle(String title);
}
