package com.pfeProject.enterpriseManagement.Repository;

import com.pfeProject.enterpriseManagement.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
