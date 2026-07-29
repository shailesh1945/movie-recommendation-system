package com.movie.recsys.dto.movie;


import lombok.Data;

@Data
public class MovieSearchRequest {

    private String title;

    private Integer genreId;

    private Integer languageId;

    private Integer releaseYear;

    private Integer page = 1;

    private Integer size = 10;

}