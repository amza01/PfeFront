package com.pfeProject.enterpriseManagement.Controller;

import com.pfeProject.enterpriseManagement.DTO.BonDeCommandeDto;
import com.pfeProject.enterpriseManagement.Model.Article;
import com.pfeProject.enterpriseManagement.Model.BonDeCommande;

import com.pfeProject.enterpriseManagement.service.BonDeCommandeService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    public BonDeCommande saveBonDeCommande(@RequestBody BonDeCommandeDto bonDeCommande) {
        return bonDeCommandeService.saveBonDeCommande(bonDeCommande);
    }

    @DeleteMapping("/{id}")
    public void deleteBonDeCommande(@PathVariable Long id) {
        bonDeCommandeService.deleteBonDeCommande(id);
    }

    @GetMapping("/rapport/{code}")
    public ResponseEntity<?>  generateReport(@PathVariable String code) throws FileNotFoundException, JRException {
        return bonDeCommandeService.exportReport("pdf",code);
    }
    @GetMapping("/update/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id ) {
        bonDeCommandeService.updateStatus(id);
        return ResponseEntity.ok("Status updated successfully.");
    }
}
