package com.movie.recsys.repository;

import com.movie.recsys.model.Movie;

import java.util.List;

public interface WatchlistRepository {
    void addToWatchlist(Integer userId, Integer movieId);

    void removeFromWatchlist(Integer userId, Integer movieId);

    boolean exists(Integer userId, Integer movieId);

    List<Movie> getUserWatchlist(Integer userId);
}
