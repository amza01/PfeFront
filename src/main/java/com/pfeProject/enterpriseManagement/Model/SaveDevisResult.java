package com.pfeProject.enterpriseManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveDevisResult {
    private boolean success;
    private Devis devis;
}
