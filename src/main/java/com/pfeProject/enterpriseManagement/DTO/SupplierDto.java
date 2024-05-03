package com.pfeProject.enterpriseManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    private String email;

    private String addresse;

    private String numeroTel;
    private String rib;
    private String cin;



}
