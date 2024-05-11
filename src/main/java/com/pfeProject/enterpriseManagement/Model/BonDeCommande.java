package com.pfeProject.enterpriseManagement.Model;

import com.pfeProject.enterpriseManagement.DTO.ArticleQuantityDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonDeCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @ManyToOne
    private Supplier fournisseur;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bondecommande_articles",
            joinColumns = @JoinColumn(name = "bondecommande_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> articles;

    @ElementCollection
    @CollectionTable(name = "bondecommande_articles_quantites")
    @MapKeyJoinColumn(name = "article_id")
    @Column(name = "quantite")
    private Map<Article, Integer> articlesQuantites;

    private String description;
    private double prixTotal;
    private String date;
    private String status; // Peut-être mieux de définir un enum pour le statut

    // Constructeurs, getters et setters

    public void calculerPrixTotal() {
        // Calculer le prix total en fonction du prix unitaire et de la quantité de chaque article
        double total = 0;
        for (Map.Entry<Article, Integer> entry : articlesQuantites.entrySet()) {
            Article article = entry.getKey();
            int quantite = entry.getValue();
            total += article.getPrixUnitaire() * quantite;
        }
        this.prixTotal = total;
    }
    public void setArticlesQuantites(List<ArticleQuantityDto> articlesQuantites) {
        // Créer une nouvelle map pour stocker les articles et leurs quantités
        Map<Article, Integer> articlesQuantitesMap = new HashMap<>();

        // Parcourir la liste des objets ArticleQuantityDto
        for (ArticleQuantityDto articleQuantity : articlesQuantites) {
            // Ajouter chaque article et sa quantité à la map
            articlesQuantitesMap.put(articleQuantity.getArticle(), articleQuantity.getQuantity());
        }

        // Assigner la map d'articles et quantités à l'attribut de la classe
        this.articlesQuantites = articlesQuantitesMap;
    }
}
