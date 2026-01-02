package com.TMDB_CLI_Tool.service;

import com.TMDB_CLI_Tool.dto.MovieDto;
import com.TMDB_CLI_Tool.dto.OmdbSearchResponse;

public interface MovieService {
    // Movie methods
    OmdbSearchResponse searchMovie(String query);
    MovieDto getMovieDetail(String imdbId);
    MovieDto getMovieDetailByTitle(String title);
    OmdbSearchResponse getPopularMovies();
    OmdbSearchResponse getTopRatedMovies();

    // TV Show methods
    OmdbSearchResponse searchSeries(String query);
    MovieDto getSeriesDetailByTitle(String title);
    OmdbSearchResponse getPopularSeries();
    OmdbSearchResponse getTopRatedSeries();
}
