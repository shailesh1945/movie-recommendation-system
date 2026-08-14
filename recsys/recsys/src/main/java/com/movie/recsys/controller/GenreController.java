package com.movie.recsys.controller;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.service.GenreService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(
            GenreService genreService) {

        this.genreService = genreService;

    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<GenreResponse>>> getAllGenres() {

        List<GenreResponse> genres =
                genreService.getAllGenres();

        return ResponseEntity.ok(

                ApiResponse.<List<GenreResponse>>builder()

                        .success(true)

                        .message("Genres fetched successfully.")

                        .data(genres)

                        .build()

        );

    }

}