package com.pfeProject.enterpriseManagement.Repository;

import com.pfeProject.enterpriseManagement.Model.BonDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonDeCommandeRepository extends JpaRepository<BonDeCommande, Long> {

}
