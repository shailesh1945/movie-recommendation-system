package com.movie.recsys.controller;


import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.user.UserResponse;
import com.movie.recsys.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUser(){

        System.out.println("PAGE CALLED");
        return ResponseEntity.ok(
                adminService.getAllUsers());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("id") Integer id) {
        System.out.println("User Deleting");
        adminService.deleteUserById(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("User deleted successfully.")
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/moviescount")
    public ResponseEntity<Integer> getMoviesCount(){
        return ResponseEntity.ok(
                adminService.getMoviesCount());
    }

    @GetMapping("/usercount")
    public ResponseEntity<Integer> getUserCount(){
        return ResponseEntity.ok(
                adminService.getUserCount());
    }

    @GetMapping("/genrecount")
    public ResponseEntity<Integer> getGenreCount(){
        return ResponseEntity.ok(
                adminService.getGenreCount());
    }
}