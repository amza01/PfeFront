package com.pfeProject.enterpriseManagement.Controller;

import com.pfeProject.enterpriseManagement.DTO.SupplierDto;
import com.pfeProject.enterpriseManagement.Model.Supplier;
import com.pfeProject.enterpriseManagement.Repository.SupplierRepository;
import com.pfeProject.enterpriseManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    SupplierService supplierService;
    @GetMapping("/getSupplier")
    public ResponseEntity<List<Supplier>> getSupplier() {

        List<Supplier> suppliers = supplierRepository.findAll();

        return ResponseEntity.ok().body(suppliers);
    }
    @PostMapping("/postSupplier")
    public ResponseEntity<?> postSupplier(@RequestBody SupplierDto supplierJson) {
        System.out.println(supplierJson);
        return supplierService.addSupplier(supplierJson);
    }

    @PutMapping("/updateSupplier/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @RequestBody SupplierDto SupplierJson) {


        // Votre logique de mise à jour ici, en utilisant le service UserService

        return supplierService.updateSupplier(id, SupplierJson);
    }
    @DeleteMapping("/deleteSupplier/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        System.out.println("Deleting user with ID: " + id);

        return supplierService.deleteSupplier(id);
    }
}
