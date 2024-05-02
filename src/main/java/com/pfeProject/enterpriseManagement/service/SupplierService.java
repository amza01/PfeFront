package com.pfeProject.enterpriseManagement.service;

import DTO.SupplierDto;
import com.pfeProject.enterpriseManagement.Model.Supplier;
import com.pfeProject.enterpriseManagement.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    public ResponseEntity<?> addSupplier(SupplierDto supplierJson)
    {
        Supplier newSupplier= mapToSupplier(supplierJson);
        supplierRepository.save(newSupplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSupplier);
    }

    private Supplier mapToSupplier(SupplierDto supplierJson) {
        Supplier supplier=new Supplier();
        supplier.setEmail(supplierJson.getEmail());
        supplier.setAddresse(supplierJson.getAddresse());
        supplier.setNumeroTel(supplierJson.getNumeroTel());

        supplier.setCin(supplierJson.getCin());
        supplier.setRib(supplierJson.getRib());
        return  supplier;
    }

    public ResponseEntity<?> updateSupplier(Long id, SupplierDto updatedSupplierData) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            Supplier existingSupplier = optionalSupplier.get();
            // Mettre à jour les champs de l'utilisateur avec les nouvelles données
            existingSupplier.setNumeroTel(updatedSupplierData.getNumeroTel());
            existingSupplier.setEmail(updatedSupplierData.getEmail());
            existingSupplier.setAddresse(updatedSupplierData.getAddresse());
            existingSupplier.setRib(updatedSupplierData.getRib());

            existingSupplier.setCin(updatedSupplierData.getCin());
            supplierRepository.save(existingSupplier);

            return ResponseEntity.ok(existingSupplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    public ResponseEntity<?> deleteSupplier(Long id) {


        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            supplierRepository.deleteById(id);
            return ResponseEntity.ok(optionalSupplier.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
