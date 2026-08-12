package com.movie.recsys.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String gender;
}
