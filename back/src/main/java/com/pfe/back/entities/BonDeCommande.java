package com.pfe.back.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BonDeCommande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fournisseur;

   
    @OneToMany(mappedBy = "bonDeCommande", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)

    private List<ArticleCommande> articles = new ArrayList<>();

    private int quantiteTotale;

    private double prixTotal;

    private LocalDate dateAuPlusTard;

    private LocalDate dateCommande;

    private boolean statut; // true = Validé, false = En attente

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(int quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public LocalDate getDateAuPlusTard() {
        return dateAuPlusTard;
    }

    public void setDateAuPlusTard(LocalDate dateAuPlusTard) {
        this.dateAuPlusTard = dateAuPlusTard;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public List<ArticleCommande> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleCommande> articles) {
        this.articles = articles;
    }
}
