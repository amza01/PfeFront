package com.pfeProject.enterpriseManagement.Repository;

import com.pfeProject.enterpriseManagement.Model.Client;
import com.pfeProject.enterpriseManagement.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
