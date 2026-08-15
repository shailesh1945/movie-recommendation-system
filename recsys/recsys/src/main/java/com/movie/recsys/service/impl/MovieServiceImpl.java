package com.movie.recsys.service.impl;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.dto.movie.MovieDetailsResponse;
import com.movie.recsys.dto.movie.MovieRequest;
import com.movie.recsys.dto.movie.MovieResponse;
import com.movie.recsys.dto.movie.MovieSearchRequest;
import com.movie.recsys.exception.ResourceNotFoundException;
import com.movie.recsys.model.Movie;
import com.movie.recsys.repository.AdminRepository;
import com.movie.recsys.repository.MovieRepository;
import com.movie.recsys.service.FileStorageService;
import com.movie.recsys.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger =
            LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    private final FileStorageService fileStorageService;

    private final AdminRepository adminRepository;

    public MovieServiceImpl(
            MovieRepository movieRepository,
            FileStorageService fileStorageService,
            AdminRepository adminRepository) {

        this.movieRepository = movieRepository;
        this.fileStorageService = fileStorageService;
        this.adminRepository = adminRepository;

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

        // -----------------------------------------------------
        // Save poster
        // -----------------------------------------------------

        if (poster != null && !poster.isEmpty()) {

            String posterUrl =
                    fileStorageService.savePoster(poster);

            request.setPosterUrl(posterUrl);

        }


        // -----------------------------------------------------
        // Convert request to Movie
        // -----------------------------------------------------

        Movie movie =
                mapToMovie(request);


        // -----------------------------------------------------
        // Save movie and get generated movie ID
        // -----------------------------------------------------

        Integer movieId =
                movieRepository.save(movie);


        // -----------------------------------------------------
        // Save movie genres
        // -----------------------------------------------------

        saveMovieGenres(
                movieId,
                request.getGenreIds()
        );


        logger.info(
                "Movie added successfully. Movie ID: {}",
                movieId
        );


        return ApiResponse.<Void>builder()

                .success(true)

                .message(
                        "Movie added successfully."
                )

                .build();

    }


    // =========================================================
    // UPDATE MOVIE
    // =========================================================

    @Override
    public ApiResponse<Void> updateMovie(
            Integer movieId,
            MovieRequest request,
            MultipartFile poster) {

        // -----------------------------------------------------
        // Check movie exists
        // -----------------------------------------------------

        Movie existing =
                movieRepository.findById(movieId);

        if (existing == null) {

            throw new ResourceNotFoundException(
                    "Movie not found."
            );

        }


        // -----------------------------------------------------
        // Save new poster if provided
        // -----------------------------------------------------

        if (poster != null && !poster.isEmpty()) {

            String posterUrl =
                    fileStorageService.savePoster(poster);

            request.setPosterUrl(posterUrl);

        } else {

            request.setPosterUrl(
                    existing.getPosterUrl()
            );

        }


        // -----------------------------------------------------
        // Update movie
        // -----------------------------------------------------

        Movie movie =
                mapToMovie(request);

        movie.setMovieId(movieId);

        movieRepository.update(movie);


        // -----------------------------------------------------
        // Update movie genres
        //
        // Delete old genres first, then insert new ones.
        // -----------------------------------------------------

        movieRepository.deleteMovieGenres(
                movieId
        );


        saveMovieGenres(
                movieId,
                request.getGenreIds()
        );


        logger.info(
                "Movie updated successfully. Movie ID: {}",
                movieId
        );


        return ApiResponse.<Void>builder()

                .success(true)

                .message(
                        "Movie updated successfully."
                )

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

        movieRepository.deleteMovieGenres(movieId);

        movieRepository.delete(movieId);

        logger.info("Movie deleted successfully");

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Movie deleted successfully.")
                .build();

    }

    @Override
    public List<GenreResponse> findGenresByMovieId(Integer movieId) {
        return List.of();
    }


    @Override
    public Integer getMoviesCount() {
        return adminRepository.getMoviesCount();
    }

    @Override
    public Integer getUserCount() {
        return adminRepository.getUserCount();
    }

    @Override
    public Integer getGenreCount()  {
        return adminRepository.getGenreCount();
    }

    // =========================================================
    // SAVE MOVIE GENRES
    // =========================================================

    private void saveMovieGenres(
            Integer movieId,
            List<Integer> genreIds) {

        if (genreIds == null ||
                genreIds.isEmpty()) {

            return;

        }


        for (Integer genreId : genreIds) {

            if (genreId == null) {

                continue;

            }

            movieRepository.saveMovieGenre(
                    movieId,
                    genreId
            );

        }

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

        List<GenreResponse> genres =
                movieRepository.findGenresByMovieId(
                        movie.getMovieId()
                );

        return MovieDetailsResponse.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseYear(movie.getReleaseYear())
                .duration(movie.getDuration())
                .director(movie.getDirector())
                .language(movie.getLanguageName())
                .languageId(movie.getLanguageId())
                .averageRating(movie.getAverageRating())
                .posterUrl(movie.getPosterUrl())
                .trailerUrl(movie.getTrailerUrl())
                .genres(genres)
                .build();

    }

}