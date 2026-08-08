package com.movie.recsys.dto.user;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String gender;

    private LocalDateTime createdAt;
}