package com.pfeProject.enterpriseManagement.Model;

import com.pfeProject.enterpriseManagement.DTO.ArticleQuantityDiscountDto;
import com.pfeProject.enterpriseManagement.DTO.ArticleQuantityDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "devis_articles",
            joinColumns = @JoinColumn(name = "devis_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> articles;
    @ManyToOne
    private Client client;
    private String status;
    private String description;
    private double prixNet;
    private double prixNetavecTaxe;
    private double prixHtSansRemiseNet;
    private double prixNetAvecRemise;
      private double prixRemise;
    private double prixTaxe;
    private double prixFinal;
    private String date;
    @ElementCollection
    @CollectionTable(name = "bondecommande_articles_quantites_discount")
    @MapKeyJoinColumn(name = "article_id")
    @Column(name = "quantite")
    private Map<Article, Integer> articlesQuantites;

    public void calculerPrixTotal() {
        // Calculer le prix total en fonction du prix unitaire et de la quantité de chaque article
        double total = 0;
        double totalTaxe = 0;

        for (Map.Entry<Article, Integer> entry : articlesQuantites.entrySet()) {
            Article article = entry.getKey();
            int quantite = entry.getValue();
          total += article.getPrixUnitaire() * quantite;
            totalTaxe=article.getPrixUnitaire()*quantite*article.getTva()/100;
        }
        this.prixNet = total;
        this.prixRemise=(total*this.client.getRemiseTVA())/100;
        this.prixNetAvecRemise=this.prixNet- this.prixRemise;
        this.prixTaxe=totalTaxe;
        this.prixNetavecTaxe=this.prixNet-  (this.prixNet*this.prixTaxe)/100;
        this.prixFinal=total+this.prixTaxe- this.prixRemise;

    }

    public void setArticlesQuantites(List<ArticleQuantityDto> articlesQuantites) {
        // Créer une nouvelle map pour stocker les articles et leurs quantités
        Map<Article, Integer> articlesQuantitesMap = new HashMap<>();

        // Parcourir la liste des objets ArticleQuantityDto
        for (ArticleQuantityDto articleQuantity : articlesQuantites) {
            // Ajouter chaque article et sa quantité à la map
            articlesQuantitesMap.put(articleQuantity.getArticle(), articleQuantity.getQuantity());
        }
        this.articlesQuantites = articlesQuantitesMap;
    }
}
