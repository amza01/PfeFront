package com.pfeProject.enterpriseManagement.service;

import com.pfeProject.enterpriseManagement.Model.Admin;
import com.pfeProject.enterpriseManagement.Model.SuperAdmin;
import com.pfeProject.enterpriseManagement.Repository.AdminRepository;
import com.pfeProject.enterpriseManagement.Repository.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private SuperAdminRepository superAdminRepository;

    public boolean verifyPassword(String email, String providedPassword) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null  ) {
            return admin.getPassword().equals(providedPassword);
        }
        return false; // User not found
    }
    public boolean isInactive(String email) {
        Admin admin = adminRepository.findByEmail(email);
        return admin != null && "inactive".equalsIgnoreCase(admin.getStatus());
    }
    public ResponseEntity<Admin> register(Admin admin) {
        admin.setStatus("inactive");
        Admin admin1=adminRepository.save(admin);
        return ResponseEntity.ok(admin1);
    }

    public ResponseEntity<Admin> update(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        Admin newAdmin=new Admin();
        if(admin.isPresent()){
             newAdmin=admin.get();
          String status=  admin.get().getStatus();

          if(status.equals("active"))
              newAdmin.setStatus("inactive");
          else
              newAdmin.setStatus("active");
        }
        return  ResponseEntity.ok(adminRepository.save(newAdmin));
    }

    public boolean Delete(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        Admin newAdmin=new Admin();
        if(admin.isPresent()){
            newAdmin=admin.get();
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public boolean verifyPasswordSuper(String email, String password) {
        SuperAdmin superAdmin = superAdminRepository.findByEmail(email);
        if (superAdmin != null  ) {
            return superAdmin.getPassword().equals(password);
        }
        return false; // User not found
}}
