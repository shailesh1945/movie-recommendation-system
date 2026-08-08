package com.movie.recsys.service.impl;

import com.movie.recsys.dto.user.UserResponse;
import com.movie.recsys.repository.AdminRepository;
import com.movie.recsys.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return adminRepository.getAllUser();
    }

    @Override
    public void deleteUserById(Integer id) {
        adminRepository.deleteUser(id);
    }
}
