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
public class Review {

    private Integer reviewId;

    private Integer movieId;

    private Integer userId;

    private String reviewText;

    private LocalDateTime reviewDate;

}