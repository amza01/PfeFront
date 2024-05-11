package com.pfeProject.enterpriseManagement.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfeProject.enterpriseManagement.DTO.ArticleQuantityDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BondeCommandePdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String fournisseur;
    private String description;
    private double prixTotal;
    private String date;
    private String Status;
    private String articlesQuantites;

    public void setArticlesQuantites(List<ArticleQuantityDto> articlesQuantites) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ArticleQuantityDto articleQuantity : articlesQuantites) {
            stringBuilder.append(articleQuantity.getArticle().getLibelle())
                    .append(":")
                    .append(articleQuantity.getQuantity())
                    .append("\n");
        }
        // Remove the last comma
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        this.articlesQuantites = stringBuilder.toString();
    }
}
