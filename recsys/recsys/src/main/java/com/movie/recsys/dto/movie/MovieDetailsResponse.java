package com.movie.recsys.dto.movie;


import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.model.Genre;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class MovieDetailsResponse {

    private Integer movieId;

    private String title;

    private String description;

    private Integer releaseYear;

    private Integer duration;

    private String director;

    private String language;

    private Integer languageId;

    private BigDecimal averageRating;

    private String posterUrl;

    private String trailerUrl;

    private List<GenreResponse> genres;

}