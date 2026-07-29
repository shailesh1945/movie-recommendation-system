package com.movie.recsys.repository;


import com.movie.recsys.model.User;

public interface AuthRepository {

    int saveUser(User user);

    User findByEmail(String email);

    User findUserWithRole(String email);

    boolean existsByEmail(String email);

}