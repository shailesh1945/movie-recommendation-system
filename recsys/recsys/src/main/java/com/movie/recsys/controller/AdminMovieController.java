package com.movie.recsys.controller;


import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.movie.MovieDetailsResponse;
import com.movie.recsys.dto.movie.MovieRequest;
import com.movie.recsys.dto.movie.MovieResponse;
import com.movie.recsys.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/movies")
public class AdminMovieController {

    private final MovieService movieService;

    public AdminMovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> addMovie(

            @Valid @ModelAttribute MovieRequest request,

            @RequestParam(value = "poster", required = false)
            MultipartFile poster

    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieService.addMovie(request, poster));

    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {

        return ResponseEntity.ok(
                movieService.getAllMovies());

    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDetailsResponse> getMovie(
            @PathVariable Integer movieId) {

        return ResponseEntity.ok(
                movieService.getMovieById(movieId));

    }

    @PutMapping(
            value = "/{movieId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<Void>> updateMovie(

            @PathVariable Integer movieId,

            @Valid @ModelAttribute MovieRequest request,

            @RequestParam(value = "poster", required = false)
            MultipartFile poster

    ) {

        return ResponseEntity.ok(
                movieService.updateMovie(
                        movieId,
                        request,
                        poster));

    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<ApiResponse<Void>> deleteMovie(
            @PathVariable Integer movieId) {

        return ResponseEntity.ok(
                movieService.deleteMovie(movieId));

    }

}
