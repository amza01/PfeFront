package com.pfeProject.enterpriseManagement.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonDeCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;

    @ManyToOne
    private Supplier fournisseur;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bonDeCommande_id")
    private List<Article> articles;

    private String description;
    private double prixTotal;
    private Date date;
    private String status;
}
