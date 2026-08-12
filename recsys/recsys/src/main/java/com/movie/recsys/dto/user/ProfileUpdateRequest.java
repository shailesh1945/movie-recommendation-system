package com.movie.recsys.dto.user;

import lombok.Data;

@Data
public class ProfileUpdateRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private String gender;

    private String password;
}
