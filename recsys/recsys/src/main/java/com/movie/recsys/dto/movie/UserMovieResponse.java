package com.movie.recsys.dto.movie;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserMovieResponse {

    private Integer movieId;

    private String title;

    private String director;

    private Integer releaseYear;

    private BigDecimal averageRating;

    private String posterUrl;

    private String language;

    private Integer score;

    private String genre;
}