package com.pfeProject.enterpriseManagement.service;

import com.pfeProject.enterpriseManagement.Model.Article;
import com.pfeProject.enterpriseManagement.Repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public Article updateArticle(Long id, Article updatedArticle) {

        // Vérifier si l'article avec l'ID donné existe dans la base de données
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + id));

        // Mettre à jour les champs de l'article existant avec les nouvelles valeurs
        existingArticle.setCode(updatedArticle.getCode());
        existingArticle.setLibelle(updatedArticle.getLibelle());
        existingArticle.setQuantiteArticle(updatedArticle.getQuantiteArticle());
existingArticle.setPrixUnitaire(updatedArticle.getPrixUnitaire());
existingArticle.setTva(updatedArticle.getTva());
        // Enregistrer les modifications dans la base de données et retourner l'article mis à jour
        return articleRepository.save(existingArticle);
    }

}
