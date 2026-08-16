package com.movie.recsys.service.impl;

import com.movie.recsys.model.Movie;
import com.movie.recsys.repository.WatchlistRepository;
import com.movie.recsys.service.WatchlistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistRepository watchlistRepository;

    public WatchlistServiceImpl(
            WatchlistRepository watchlistRepository) {

        this.watchlistRepository =
                watchlistRepository;
    }

    @Override
    public void addMovie(
            Integer userId,
            Integer movieId) {

        if (watchlistRepository.exists(
                userId,
                movieId)) {

            throw new RuntimeException(
                    "Movie already exists in My List"
            );
        }

        watchlistRepository.addToWatchlist(
                userId,
                movieId
        );
    }

    @Override
    public void removeMovie(
            Integer userId,
            Integer movieId) {

        watchlistRepository.removeFromWatchlist(
                userId,
                movieId
        );
    }

    @Override
    public boolean isMovieInWatchlist(
            Integer userId,
            Integer movieId) {

        return watchlistRepository.exists(
                userId,
                movieId
        );
    }

    @Override
    public List<Movie> getMyList(
            Integer userId) {

        return watchlistRepository
                .getUserWatchlist(userId);
    }
}
