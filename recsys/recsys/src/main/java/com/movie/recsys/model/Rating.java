package com.movie.recsys.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private Integer ratingId;

    private Integer movieId;

    private Integer userId;

    private Integer rating;

    private LocalDateTime ratedAt;

}