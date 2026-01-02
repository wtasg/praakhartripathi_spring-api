package com.TMDB_CLI_Tool.cotroller;

import com.TMDB_CLI_Tool.dto.MovieDto;
import com.TMDB_CLI_Tool.dto.OmdbSearchResponse;
import com.TMDB_CLI_Tool.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // --- Movie Endpoints ---

    @GetMapping("/movies/search")
    public OmdbSearchResponse searchMovie(@RequestParam String query) {
        return movieService.searchMovie(query);
    }

    @GetMapping("/movies/{imdbId}")
    public MovieDto getMovieDetail(@PathVariable String imdbId) {
        return movieService.getMovieDetail(imdbId);
    }

    @GetMapping("/movies/detail")
    public MovieDto getMovieDetailByTitle(@RequestParam String title) {
        return movieService.getMovieDetailByTitle(title);
    }

    @GetMapping("/movies/popular")
    public OmdbSearchResponse getPopularMovies() {
        return movieService.getPopularMovies();
    }

    @GetMapping("/movies/top-rated")
    public OmdbSearchResponse getTopRatedMovies() {
        return movieService.getTopRatedMovies();
    }

    // --- TV Show Endpoints ---

    @GetMapping("/series/search")
    public OmdbSearchResponse searchSeries(@RequestParam String query) {
        return movieService.searchSeries(query);
    }

    @GetMapping("/series/detail")
    public MovieDto getSeriesDetailByTitle(@RequestParam String title) {
        return movieService.getSeriesDetailByTitle(title);
    }

    @GetMapping("/series/popular")
    public OmdbSearchResponse getPopularSeries() {
        return movieService.getPopularSeries();
    }

    @GetMapping("/series/top-rated")
    public OmdbSearchResponse getTopRatedSeries() {
        return movieService.getTopRatedSeries();
    }
}
