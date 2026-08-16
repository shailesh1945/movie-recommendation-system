package com.movie.recsys.service;

import com.movie.recsys.model.Movie;

import java.util.List;

public interface WatchlistService {
    void addMovie(Integer userId, Integer movieId);

    void removeMovie(Integer userId, Integer movieId);

    boolean isMovieInWatchlist(Integer userId, Integer movieId);

    List<Movie> getMyList(Integer userId);
}
