package com.movie.recsys.controller;



import com.movie.recsys.dto.movie.MovieDetailsResponse;
import com.movie.recsys.dto.movie.MovieResponse;
import com.movie.recsys.dto.movie.MovieSearchRequest;
import com.movie.recsys.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        System.out.println("Api called successfully");
        return ResponseEntity.ok(
                movieService.getAllMovies());

    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDetailsResponse> getMovie(
            @PathVariable Integer movieId) {

        return ResponseEntity.ok(
                movieService.getMovieById(movieId));

    }

    @PostMapping("/search")
    public ResponseEntity<List<MovieResponse>> searchMovies(
            @RequestBody MovieSearchRequest request) {

        return ResponseEntity.ok(
                movieService.searchMovies(request));

    }

    @GetMapping("/latest")
    public ResponseEntity<List<MovieResponse>> latestMovies() {

        return ResponseEntity.ok(
                movieService.latestMovies());

    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<MovieResponse>> topRatedMovies() {

        return ResponseEntity.ok(
                movieService.topRatedMovies());

    }

}
