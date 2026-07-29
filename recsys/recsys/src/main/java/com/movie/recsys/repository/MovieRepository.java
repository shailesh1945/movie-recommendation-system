package com.movie.recsys.repository;


import com.movie.recsys.dto.movie.MovieSearchRequest;
import com.movie.recsys.model.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> findAll();

    Movie findById(Integer movieId);

    List<Movie> search(MovieSearchRequest request);

    int save(Movie movie);

    int update(Movie movie);

    int delete(Integer movieId);

    List<Movie> latestMovies();

    List<Movie> topRatedMovies();

}
