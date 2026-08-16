package com.movie.recsys.controller;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.model.Rating;
import com.movie.recsys.security.SecurityUtil;
import com.movie.recsys.service.RatingService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(
            RatingService ratingService) {

        this.ratingService =
                ratingService;
    }


    // ==========================================
    // Get current user's rating
    // ==========================================

    @GetMapping("/movie/{movieId}/my-rating")
    public ResponseEntity<ApiResponse<Rating>> getMyRating(

            @PathVariable Integer movieId,

            Authentication authentication) {

        Integer userId =
                SecurityUtil.getUserId(authentication);

        return ResponseEntity.ok(
                ratingService.getMyRating(
                        movieId,
                        userId
                )
        );
    }


    // ==========================================
    // Save / Update rating
    // ==========================================

    @PostMapping("/movie/{movieId}")
    public ResponseEntity<ApiResponse<Rating>> saveRating(

            @PathVariable Integer movieId,

            @RequestParam Integer rating,

            Authentication authentication) {

        Integer userId =
                SecurityUtil.getUserId(authentication);

        return ResponseEntity.ok(
                ratingService.saveRating(
                        movieId,
                        userId,
                        rating
                )
        );
    }


    // ==========================================
    // Average rating
    // ==========================================

    @GetMapping("/movie/{movieId}/average")
    public ResponseEntity<ApiResponse<Double>> getAverageRating(

            @PathVariable Integer movieId) {

        return ResponseEntity.ok(
                ratingService.getAverageRating(
                        movieId
                )
        );
    }


    // ==========================================
    // Rating count
    // ==========================================

    @GetMapping("/movie/{movieId}/count")
    public ResponseEntity<ApiResponse<Integer>> getRatingCount(

            @PathVariable Integer movieId) {

        return ResponseEntity.ok(
                ratingService.getRatingCount(
                        movieId
                )
        );
    }
} 