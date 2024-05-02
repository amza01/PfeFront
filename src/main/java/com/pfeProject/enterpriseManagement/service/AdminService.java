package com.pfeProject.enterpriseManagement.service;

import com.pfeProject.enterpriseManagement.Model.Admin;
import com.pfeProject.enterpriseManagement.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public boolean verifyPassword(String email, String providedPassword) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null  ) {
            return admin.getPassword().equals(providedPassword);
        }
        return false; // User not found
    }
}
