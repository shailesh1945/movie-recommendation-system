package com.movie.recsys.controller;

import com.movie.recsys.service.WatchlistService;
import com.movie.recsys.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;
    private final JwtUtil jwtUtil;

    public WatchlistController(
            WatchlistService watchlistService,
            JwtUtil jwtUtil) {

        this.watchlistService =
                watchlistService;

        this.jwtUtil =
                jwtUtil;
    }


    @PostMapping("/{movieId}")
    public ResponseEntity<?> addMovie(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Integer movieId) {

        Integer userId =
                getUserIdFromToken(authorization);

        watchlistService.addMovie(
                userId,
                movieId
        );

        return ResponseEntity.ok(
                "Movie added to My List"
        );
    }


    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> removeMovie(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Integer movieId) {

        Integer userId =
                getUserIdFromToken(authorization);

        watchlistService.removeMovie(
                userId,
                movieId
        );

        return ResponseEntity.ok(
                "Movie removed from My List"
        );
    }


    @GetMapping("/check/{movieId}")
    public ResponseEntity<?> checkMovie(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Integer movieId) {

        Integer userId =
                getUserIdFromToken(authorization);

        boolean exists =
                watchlistService
                        .isMovieInWatchlist(
                                userId,
                                movieId
                        );

        return ResponseEntity.ok(exists);
    }


    @GetMapping
    public ResponseEntity<?> getMyList(
            @RequestHeader("Authorization") String authorization) {

        Integer userId =
                getUserIdFromToken(authorization);

        return ResponseEntity.ok(
                watchlistService
                        .getMyList(userId)
        );
    }


    private Integer getUserIdFromToken(
            String authorization) {

        if (authorization == null ||
                !authorization.startsWith("Bearer ")) {

            throw new RuntimeException(
                    "Invalid authorization header"
            );
        }

        String token =
                authorization.substring(7);

        if (!jwtUtil.isTokenValid(token)) {

            throw new RuntimeException(
                    "Invalid or expired token"
            );
        }

        return jwtUtil.extractUserId(token);
    }
}