package com.movie.recsys.controller;


import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.movie.MovieRequest;
import com.movie.recsys.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
public class AdminMovieController {

    private final MovieService movieService;

    public AdminMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addMovie(
            @Valid @RequestBody MovieRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieService.addMovie(request));

    }

    @PutMapping("/{movieId}")
    public ResponseEntity<ApiResponse<Void>> updateMovie(
            @PathVariable Integer movieId,
            @Valid @RequestBody MovieRequest request) {

        return ResponseEntity.ok(
                movieService.updateMovie(
                        movieId,
                        request));

    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<ApiResponse<Void>> deleteMovie(
            @PathVariable Integer movieId) {

        return ResponseEntity.ok(
                movieService.deleteMovie(movieId));

    }

}
