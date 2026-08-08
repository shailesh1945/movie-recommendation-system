package com.movie.recsys.repository.impl;

import com.movie.recsys.constant.SqlConstants;
import com.movie.recsys.dto.recommendation.RecommendationResponse;
import com.movie.recsys.mapper.RecommendationRowMapper;
import com.movie.recsys.repository.RecommendationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommendationRepositoryImpl
        implements RecommendationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RecommendationRowMapper rowMapper;

    public RecommendationRepositoryImpl(

            JdbcTemplate jdbcTemplate,

            RecommendationRowMapper rowMapper) {

        this.jdbcTemplate = jdbcTemplate;

        this.rowMapper = rowMapper;

    }

    @Override
    public List<RecommendationResponse>
    getRecommendations(Integer userId) {

        return jdbcTemplate.query(

                SqlConstants.GET_RECOMMENDATIONS,

                rowMapper,

                userId

        );

    }

}