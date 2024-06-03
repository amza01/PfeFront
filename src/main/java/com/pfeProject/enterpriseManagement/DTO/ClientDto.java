package com.pfeProject.enterpriseManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String email;

    private String addresse;

    private String numeroTel;
    private Long tauxRemise;



}
