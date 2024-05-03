package com.pfeProject.enterpriseManagement.Controller;

import com.pfeProject.enterpriseManagement.DTO.LoginRequest;
import com.pfeProject.enterpriseManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
 if (adminService.verifyPassword(loginRequest.getEmail(), loginRequest.getPassword())) {
            return ResponseEntity.ok().body("{\"message\": \"Login successful\"}");

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid username or password\"}");
        }
    }
}
