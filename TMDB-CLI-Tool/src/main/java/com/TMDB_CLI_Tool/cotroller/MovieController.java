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
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public OmdbSearchResponse searchMovie(@RequestParam String query) {
        return movieService.searchMovie(query);
    }

    @GetMapping("/{imdbId}")
    public MovieDto getMovieDetail(@PathVariable String imdbId) {
        return movieService.getMovieDetail(imdbId);
    }

    @GetMapping("/detail")
    public MovieDto getMovieDetailByTitle(@RequestParam String title) {
        return movieService.getMovieDetailByTitle(title);
    }
}
