package com.movie.recsys.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

}