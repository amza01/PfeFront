package com.pfeProject.enterpriseManagement.Repository;

import com.pfeProject.enterpriseManagement.Model.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {

    SuperAdmin findByEmail(String email);
}
