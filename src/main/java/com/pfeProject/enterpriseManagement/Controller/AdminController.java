package com.pfeProject.enterpriseManagement.Controller;

import com.pfeProject.enterpriseManagement.DTO.LoginRequest;
import com.pfeProject.enterpriseManagement.Model.Admin;
import com.pfeProject.enterpriseManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (adminService.verifyPassword(email, password)) {
            if (adminService.isInactive(email)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\": \"Your account is inactive.\"}");
            }
            return ResponseEntity.ok().body("{\"message\": \"Login successful\"}");
        } else {
            if(adminService.verifyPasswordSuper(email, password))
            {
                return ResponseEntity.ok().body("{\"message\": \"Login superAdmin successful\"}");
            }
            else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Invalid username or password\"}");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {

        return this.adminService.register(admin);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Admin> Delete(@PathVariable Long id) {
boolean result=this.adminService.Delete(id);
       if(result==true)
           return ResponseEntity.ok(null);
       return ResponseEntity.ok(null);
    }
    @GetMapping("/updateStatus/{id}")
    public ResponseEntity<Admin> updateStatus(@PathVariable Long id) {

        return adminService.update(id);
    }
    @GetMapping("/getAll")
    public List<Admin> getAll() {

        return adminService.getAllAdmin();
    }
}
