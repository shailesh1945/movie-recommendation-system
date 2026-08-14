package com.movie.recsys.repository;


import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.dto.movie.MovieSearchRequest;
import com.movie.recsys.model.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> findAll();

    Movie findById(Integer movieId);

    List<Movie> search(MovieSearchRequest request);

    Integer save(Movie movie);

    int update(Movie movie);

    int delete(Integer movieId);

    List<Movie> latestMovies();

    List<Movie> topRatedMovies();

    List<GenreResponse> findGenresByMovieId(
            Integer movieId
    );

    void saveMovieGenre(
            Integer movieId,
            Integer genreId
    );

    void deleteMovieGenres(
            Integer movieId
    );



}
