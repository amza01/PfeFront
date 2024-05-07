package com.pfeProject.enterpriseManagement.Controller;

import com.pfeProject.enterpriseManagement.Model.BonDeCommande;

import com.pfeProject.enterpriseManagement.Security.BonDeCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bon-de-commande")
public class BonDeCommandeController {

    private final BonDeCommandeService bonDeCommandeService;

    @Autowired
    public BonDeCommandeController(BonDeCommandeService bonDeCommandeService) {
        this.bonDeCommandeService = bonDeCommandeService;
    }

    @GetMapping
    public List<BonDeCommande> getAllBonDeCommandes() {
        return bonDeCommandeService.getAllBonDeCommandes();
    }

    @GetMapping("/{id}")
    public Optional<BonDeCommande> getBonDeCommandeById(@PathVariable Long id) {
        return bonDeCommandeService.getBonDeCommandeById(id);
    }

    @PostMapping
    public BonDeCommande saveBonDeCommande(@RequestBody BonDeCommande bonDeCommande) {
        return bonDeCommandeService.saveBonDeCommande(bonDeCommande);
    }

    @DeleteMapping("/{id}")
    public void deleteBonDeCommande(@PathVariable Long id) {
        bonDeCommandeService.deleteBonDeCommande(id);
    }
}
