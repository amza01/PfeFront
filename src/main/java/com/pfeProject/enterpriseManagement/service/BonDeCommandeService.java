package com.pfeProject.enterpriseManagement.service;

import com.pfeProject.enterpriseManagement.DTO.ArticleQuantityDto;
import com.pfeProject.enterpriseManagement.DTO.BonDeCommandeDto;
import com.pfeProject.enterpriseManagement.Model.Article;
import com.pfeProject.enterpriseManagement.Model.BonDeCommande;
import com.pfeProject.enterpriseManagement.Model.BondeCommandePdf;
import com.pfeProject.enterpriseManagement.Repository.ArticleRepository;
import com.pfeProject.enterpriseManagement.Repository.BonDeCommandeRepository;
import com.pfeProject.enterpriseManagement.Repository.bonDeCommandePdfRepository;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

@Service
public class BonDeCommandeService {

    private final BonDeCommandeRepository bonDeCommandeRepository;
    private final ArticleRepository articleRepository;
    private final bonDeCommandePdfRepository bonDecommandePdfRepository;


    @Autowired
    public BonDeCommandeService(BonDeCommandeRepository bonDeCommandeRepository, bonDeCommandePdfRepository bonDecommandePdfRepository,ArticleRepository articleRepository) {
        this.bonDeCommandeRepository = bonDeCommandeRepository;
        this.bonDecommandePdfRepository = bonDecommandePdfRepository;
        this.articleRepository = articleRepository;
    }



    public List<BonDeCommande> getAllBonDeCommandes() {
        return bonDeCommandeRepository.findAll();
    }

    public Optional<BonDeCommande> getBonDeCommandeById(Long id) {
        return bonDeCommandeRepository.findById(id);
    }

    public BonDeCommande saveBonDeCommande(BonDeCommandeDto bonDeCommandeDto) {
        // Créer un nouvel objet BonDeCommande
        BonDeCommande bonDeCommande = new BonDeCommande();
        bonDeCommande.setCode(bonDeCommandeDto.getCode());
        bonDeCommande.setFournisseur(bonDeCommandeDto.getFournisseur());
        bonDeCommande.setArticles(bonDeCommandeDto.getArticles());
        bonDeCommande.setArticlesQuantites(bonDeCommandeDto.getArticlesQuantites());
        bonDeCommande.setDescription(bonDeCommandeDto.getDescription());
        bonDeCommande.setDate(bonDeCommandeDto.getDate());
        bonDeCommande.setStatus(bonDeCommandeDto.getStatus());

        // Calculer le prix total du bon de commande
        bonDeCommande.calculerPrixTotal();
        BondeCommandePdf bondeCommandePdf=new BondeCommandePdf();
        bondeCommandePdf.setCode(bonDeCommandeDto.getCode());
        bondeCommandePdf.setFournisseur(bonDeCommandeDto.getFournisseur().getEmail());
        bondeCommandePdf.setDate(bonDeCommandeDto.getDate());
        bondeCommandePdf.setPrixTotal(bonDeCommande.getPrixTotal());
        bondeCommandePdf.setDescription(bonDeCommandeDto.getDescription());
        bondeCommandePdf.setArticlesQuantites(bonDeCommandeDto.getArticlesQuantites());
        bondeCommandePdf.setStatus(bonDeCommandeDto.getStatus());
        bonDecommandePdfRepository.save(bondeCommandePdf);
        // Enregistrer le bon de commande dans la base de données
        return bonDeCommandeRepository.save(bonDeCommande);


    }

    public void deleteBonDeCommande(Long id) {
        bonDeCommandeRepository.deleteById(id);
    }
    public ResponseEntity<?> exportReport(String reportFormat, String code) throws FileNotFoundException, JRException {
        Optional<BondeCommandePdf> employees = bonDecommandePdfRepository.findByCode(code);
        String path = "C:\\Users\\benmo\\Pdf";
        String path1="classpath:BonDeCommandeReportt.jrxml";
        if(employees.isPresent()){


            BondeCommandePdf bondeCommandePdf= employees.get();
            List<BondeCommandePdf> list = new ArrayList<>();
            list.add(bondeCommandePdf);
if(bondeCommandePdf.getStatus().equals("reçu")){
    path1="classpath:BonDeCommandeReport.jrxml";
}

        //load file and compile it
        File file = ResourceUtils.getFile(path1);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\BonDeCommande.pdf");
        }
        }
        return ResponseEntity.ok("report generated in path : " + path);
    }
    public BonDeCommande updateStatus(Long bonDeCommandeId) {
        Optional<BonDeCommande> optionalBonDeCommande = bonDeCommandeRepository.findById(bonDeCommandeId);
        if (optionalBonDeCommande.isPresent()) {
            BonDeCommande bonDeCommande = optionalBonDeCommande.get();
            bonDeCommande.setStatus("reçu");
            Map<Article, Integer> articlesQuantites = bonDeCommande.getArticlesQuantites();
            for (Map.Entry<Article, Integer> entry : articlesQuantites.entrySet()) {
                Article article = entry.getKey();
                int newQuantity = entry.getValue();

                // Mettre à jour la quantité dans la table Article (quantité + nouvelle quantité)
                article.setQuantiteArticle(article.getQuantiteArticle() + newQuantity);
                articleRepository.save(article);
            }
            Optional<BondeCommandePdf> optionalBonDeCommandePdf =  bonDecommandePdfRepository.findByCode(bonDeCommande.getCode());
if(optionalBonDeCommandePdf.isPresent())
{
    BondeCommandePdf BondeCommandePdf=optionalBonDeCommandePdf.get();
    BondeCommandePdf.setStatus("reçu");
    bonDecommandePdfRepository.save(BondeCommandePdf);
}
            return bonDeCommandeRepository.save(bonDeCommande);
        } else {
            throw new RuntimeException("Bon de commande non trouvé avec l'ID: " + bonDeCommandeId);
        }
    }
}
