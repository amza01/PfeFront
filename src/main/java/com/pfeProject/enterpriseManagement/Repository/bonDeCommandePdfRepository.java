package com.pfeProject.enterpriseManagement.Repository;

import com.pfeProject.enterpriseManagement.Model.BondeCommandePdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface bonDeCommandePdfRepository extends JpaRepository<BondeCommandePdf, Long> {

    Optional<BondeCommandePdf> findByCode(String code);
}
