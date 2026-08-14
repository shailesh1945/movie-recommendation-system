package com.movie.recsys.repository.impl;

import com.movie.recsys.constant.SqlConstants;
import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.mapper.GenreRowMapper;
import com.movie.recsys.repository.GenreRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    private final JdbcTemplate jdbcTemplate;

    private final GenreRowMapper genreRowMapper;

    public GenreRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            GenreRowMapper genreRowMapper) {

        this.jdbcTemplate = jdbcTemplate;

        this.genreRowMapper = genreRowMapper;
    }


    @Override
    public List<GenreResponse> getAllGenres() {

        return jdbcTemplate.query(

                SqlConstants.GET_ALL_GENRES,

                genreRowMapper

        );

    }



}