package com.movie.recsys.service;

import com.movie.recsys.dto.genre.GenreResponse;

import java.util.List;

public interface GenreService {

    List<GenreResponse> getAllGenres();

}