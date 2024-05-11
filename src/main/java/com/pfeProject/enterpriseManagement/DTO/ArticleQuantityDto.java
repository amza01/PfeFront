package com.pfeProject.enterpriseManagement.DTO;

import com.pfeProject.enterpriseManagement.Model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQuantityDto {
    private Article article;
    private int quantity;
    @Override
    public String toString() {
        return "Article: " + article + ", Quantity: " + quantity;
    }
}
