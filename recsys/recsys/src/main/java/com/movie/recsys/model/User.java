package com.movie.recsys.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;

    private Integer roleId;

    private String roleName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String mobile;

    private String gender;

    private LocalDate dob;

    private String profileImage;

    private String status;

    private LocalDateTime createdAt;

}