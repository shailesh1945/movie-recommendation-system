package com.movie.recsys.repository;

import com.movie.recsys.dto.genre.GenreResponse;

import java.util.List;

public interface GenreRepository {

    List<GenreResponse> getAllGenres();



}