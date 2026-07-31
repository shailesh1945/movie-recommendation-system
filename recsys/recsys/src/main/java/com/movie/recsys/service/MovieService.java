package com.movie.recsys.service;


import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.movie.MovieDetailsResponse;
import com.movie.recsys.dto.movie.MovieRequest;
import com.movie.recsys.dto.movie.MovieResponse;
import com.movie.recsys.dto.movie.MovieSearchRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {

    List<MovieResponse> getAllMovies();

    MovieDetailsResponse getMovieById(Integer movieId);

    List<MovieResponse> searchMovies(MovieSearchRequest request);

    List<MovieResponse> latestMovies();

    List<MovieResponse> topRatedMovies();

    ApiResponse<Void> addMovie(
            MovieRequest request,
            MultipartFile poster);

    ApiResponse<Void> updateMovie(
            Integer movieId,
            MovieRequest request,
            MultipartFile poster);

    ApiResponse<Void> deleteMovie(Integer movieId);


}
