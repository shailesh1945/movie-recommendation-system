package com.movie.recsys.controller;

import com.movie.recsys.dto.movie.UserMovieResponse;
import com.movie.recsys.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usermovies")
public class UserMovieController {

    private final UserService userService;

    public UserMovieController(
            UserService userService) {

        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<List<UserMovieResponse>>
    getAllMovies(){
        return ResponseEntity.ok(
                userService.getAllMovies());
    }

}
