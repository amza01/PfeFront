package com.pfeProject.enterpriseManagement.Repository;

import com.pfeProject.enterpriseManagement.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByEmail(String email);
}
