package com.pfe.back.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ArticleSousStock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nom;
    private String description;
    private int quantiteDisponibleSousStock; 

    private double prixUnitaire;
    private Long sousStockId;

    @Column(name = "date_expiration")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateExpiration;


    @Column(name = "reference")
    private String reference;

    @Column(name = "fournisseur")
    private String fournisseur;

   


    // Getters and Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
    public int getQuantiteDisponibleSousStock() {
        return quantiteDisponibleSousStock;
    }

    public void setQuantiteDisponibleSousStock(int quantiteDisponibleSousStock) {
        this.quantiteDisponibleSousStock = quantiteDisponibleSousStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


 

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }


  

    public Long getSousStockId() {
        return sousStockId;
    }

    public void setSousStockId(Long sousStockId) {
        this.sousStockId = sousStockId;
    }
}
