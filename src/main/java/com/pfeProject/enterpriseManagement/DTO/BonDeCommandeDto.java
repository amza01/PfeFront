package com.pfeProject.enterpriseManagement.DTO;

import com.pfeProject.enterpriseManagement.Model.Article;
import com.pfeProject.enterpriseManagement.Model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonDeCommandeDto {

    private String code;
    private Supplier fournisseur;
    private List<Article> articles;
    private List<ArticleQuantityDto> articlesQuantites;
    private String description;
    private String date;
    private String status;

    // Autres attributs et méthodes si nécessaire
}
