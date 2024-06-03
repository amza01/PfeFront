package com.pfeProject.enterpriseManagement.Controller;

import com.pfeProject.enterpriseManagement.Model.Devis;
import com.pfeProject.enterpriseManagement.Model.SaveDevisResult;
import com.pfeProject.enterpriseManagement.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @PostMapping
    public ResponseEntity<?> saveDevis(@RequestBody Devis devis) {
        SaveDevisResult result = devisService.saveDevis(devis);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getDevis(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Insufficient stock for one or more articles", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Devis>> getAllDevis() {
        List<Devis> devisList = devisService.getAllDevis();
        return new ResponseEntity<>(devisList, HttpStatus.OK);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id ) {
        devisService.updateStatus(id);
        return ResponseEntity.ok("Status updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Devis> deleteDevis(@PathVariable Long id) {
        devisService.deleteDevis(id);
       return devisService.deleteDevis(id);
    }
}
