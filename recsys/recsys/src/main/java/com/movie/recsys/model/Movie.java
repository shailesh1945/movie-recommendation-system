package com.movie.recsys.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Integer movieId;

    private String title;

    private String description;

    private Integer releaseYear;

    private Integer duration;

    private String director;

    private Integer languageId;

    private String languageName;

    private String posterUrl;

    private String trailerUrl;

    private BigDecimal averageRating;

    private LocalDateTime createdAt;

}