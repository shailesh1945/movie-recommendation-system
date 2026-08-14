package com.movie.recsys.service.impl;

import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.repository.GenreRepository;
import com.movie.recsys.service.GenreService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(
            GenreRepository genreRepository) {

        this.genreRepository = genreRepository;

    }


    @Override
    public List<GenreResponse> getAllGenres() {

        return genreRepository.getAllGenres();

    }

}