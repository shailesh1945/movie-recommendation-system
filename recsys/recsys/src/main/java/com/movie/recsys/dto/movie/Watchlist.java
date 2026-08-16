package com.movie.recsys.dto.movie;

import lombok.Builder;
import lombok.Data;

import java.security.Timestamp;

@Data
@Builder
public class Watchlist {
    private int watchlistId;
    private int movieId;
    private int userId;
    private Timestamp createdAt;

}
