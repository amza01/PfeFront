package com.pfeProject.enterpriseManagement.Security;

import com.pfeProject.enterpriseManagement.Model.BonDeCommande;
import com.pfeProject.enterpriseManagement.Repository.BonDeCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BonDeCommandeService {

    private final BonDeCommandeRepository bonDeCommandeRepository;

    @Autowired
    public BonDeCommandeService(BonDeCommandeRepository bonDeCommandeRepository) {
        this.bonDeCommandeRepository = bonDeCommandeRepository;
    }

    public List<BonDeCommande> getAllBonDeCommandes() {
        return bonDeCommandeRepository.findAll();
    }

    public Optional<BonDeCommande> getBonDeCommandeById(Long id) {
        return bonDeCommandeRepository.findById(id);
    }

    public BonDeCommande saveBonDeCommande(BonDeCommande bonDeCommande) {
        return bonDeCommandeRepository.save(bonDeCommande);
    }

    public void deleteBonDeCommande(Long id) {
        bonDeCommandeRepository.deleteById(id);
    }
}
