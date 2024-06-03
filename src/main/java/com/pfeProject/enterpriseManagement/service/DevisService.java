package com.pfeProject.enterpriseManagement.service;

import com.pfeProject.enterpriseManagement.Model.Article;
import com.pfeProject.enterpriseManagement.Model.Devis;
import com.pfeProject.enterpriseManagement.Model.SaveDevisResult;
import com.pfeProject.enterpriseManagement.Repository.ArticleRepository;
import com.pfeProject.enterpriseManagement.Repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;
    @Autowired
    ArticleRepository articleRepository ;
    @Transactional
    public SaveDevisResult saveDevis(Devis devis) {
        boolean stockIssue = false;
        devis.calculerPrixTotal();

        for (Map.Entry<Article, Integer> entry : devis.getArticlesQuantites().entrySet()) {
            Article article = entry.getKey();
            int quantite = entry.getValue();
            if ((article.getQuantiteArticle() - quantite) < 0) {

                stockIssue = true;
                break;
            }
        }

        if (stockIssue) {
            return new SaveDevisResult(false, null);
        } else {
            Devis savedDevis = devisRepository.save(devis);
            return new SaveDevisResult(true, savedDevis);
        }
    }

    public List<Devis> getAllDevis() {
        return devisRepository.findAll();
    }



    public ResponseEntity<Devis> deleteDevis(Long id) {
        Optional<Devis> devis = devisRepository.findById(id);
            devisRepository.delete(devis.get());
        return ResponseEntity.ok( null);
    }

    public void updateStatus(Long id) {
        Optional<Devis>devis=devisRepository.findById(id);
        if(devis.isPresent())
        {   Devis newDevi=devis.get();
            newDevi.setStatus("reçu");
            for (Map.Entry<Article, Integer> entry : newDevi.getArticlesQuantites().entrySet()) {
                Article article = entry.getKey();
                int quantite = entry.getValue();
                System.out.println(quantite);
                System.out.println(article.getQuantiteArticle() - quantite);
                article.setQuantiteArticle(article.getQuantiteArticle() - quantite);
                articleRepository.save(article);


            }
         devisRepository.save(newDevi);
        }


    }
}
