package com.movie.recsys.service.impl;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.movie.MovieDetailsResponse;
import com.movie.recsys.dto.movie.MovieRequest;
import com.movie.recsys.dto.movie.MovieResponse;
import com.movie.recsys.dto.movie.MovieSearchRequest;
import com.movie.recsys.exception.ResourceNotFoundException;
import com.movie.recsys.model.Movie;
import com.movie.recsys.repository.MovieRepository;
import com.movie.recsys.service.FileStorageService;
import com.movie.recsys.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger =
            LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    private final FileStorageService fileStorageService;

    public MovieServiceImpl(
            MovieRepository movieRepository,
            FileStorageService fileStorageService) {

        this.movieRepository = movieRepository;
        this.fileStorageService = fileStorageService;

    }

    @Override
    public List<MovieResponse> getAllMovies() {

        logger.info("Fetching all movies");

        return movieRepository.findAll()
                .stream()
                .map(this::mapToMovieResponse)
                .toList();

    }

    @Override
    public MovieDetailsResponse getMovieById(Integer movieId) {

        logger.info("Fetching movie {}", movieId);

        Movie movie = movieRepository.findById(movieId);

        if (movie == null) {

            throw new ResourceNotFoundException(
                    "Movie not found."
            );

        }

        return mapToMovieDetails(movie);

    }

    @Override
    public List<MovieResponse> searchMovies(
            MovieSearchRequest request) {

        logger.info("Searching movies");

        return movieRepository.search(request)
                .stream()
                .map(this::mapToMovieResponse)
                .toList();

    }

    @Override
    public List<MovieResponse> latestMovies() {

        return movieRepository.latestMovies()
                .stream()
                .map(this::mapToMovieResponse)
                .toList();

    }

    @Override
    public List<MovieResponse> topRatedMovies() {

        return movieRepository.topRatedMovies()
                .stream()
                .map(this::mapToMovieResponse)
                .toList();

    }

    @Override
    public ApiResponse<Void> addMovie(
            MovieRequest request,
            MultipartFile poster) {

        if (poster != null && !poster.isEmpty()) {

            String posterUrl =
                    fileStorageService.savePoster(poster);

            request.setPosterUrl(posterUrl);

        }

        Movie movie = mapToMovie(request);

        movieRepository.save(movie);

        logger.info("Movie added successfully");

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Movie added successfully.")
                .build();

    }

    @Override
    public ApiResponse<Void> updateMovie(
            Integer movieId,
            MovieRequest request,
            MultipartFile poster) {

        Movie existing =
                movieRepository.findById(movieId);

        if (existing == null) {

            throw new ResourceNotFoundException(
                    "Movie not found."
            );

        }

        if (poster != null && !poster.isEmpty()) {

            String posterUrl =
                    fileStorageService.savePoster(poster);

            request.setPosterUrl(posterUrl);

        } else {

            request.setPosterUrl(existing.getPosterUrl());

        }

        Movie movie = mapToMovie(request);

        movie.setMovieId(movieId);

        movieRepository.update(movie);

        logger.info("Movie updated successfully");

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Movie updated successfully.")
                .build();

    }

    @Override
    public ApiResponse<Void> deleteMovie(
            Integer movieId) {

        Movie existing =
                movieRepository.findById(movieId);

        if (existing == null) {

            throw new ResourceNotFoundException(
                    "Movie not found."
            );

        }

        movieRepository.delete(movieId);

        logger.info("Movie deleted successfully");

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Movie deleted successfully.")
                .build();

    }

    // ==========================================
    // Private Mapping Methods
    // ==========================================

    private Movie mapToMovie(MovieRequest request) {

        return Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .releaseYear(request.getReleaseYear())
                .duration(request.getDuration())
                .director(request.getDirector())
                .languageId(request.getLanguageId())
                .posterUrl(request.getPosterUrl())
                .trailerUrl(request.getTrailerUrl())
                .build();

    }

    private MovieResponse mapToMovieResponse(
            Movie movie) {

        return MovieResponse.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .duration(movie.getDuration())
                .director(movie.getDirector())
                .language(movie.getLanguageName())
                .averageRating(movie.getAverageRating())
                .posterUrl(movie.getPosterUrl())
                .build();

    }

    private MovieDetailsResponse mapToMovieDetails(
            Movie movie) {

        return MovieDetailsResponse.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseYear(movie.getReleaseYear())
                .duration(movie.getDuration())
                .director(movie.getDirector())
                .language(movie.getLanguageName())
                .averageRating(movie.getAverageRating())
                .posterUrl(movie.getPosterUrl())
                .trailerUrl(movie.getTrailerUrl())
                .genres(Collections.emptyList())
                .build();

    }

}