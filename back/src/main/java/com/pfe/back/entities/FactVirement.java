package com.pfe.back.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FactVirement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String NomFournisseur; 
    
    private String facturePath; 
    private LocalDate factureDate;

    private String virementPath; 
    private LocalDate virementDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFacturePath() {
        return facturePath;
    }

    public void setFacturePath(String facturePath) {
        this.facturePath = facturePath;
    }

    public LocalDate getFactureDate() {
        return factureDate;
    }

    public void setFactureDate(LocalDate factureDate) {
        this.factureDate = factureDate;
    }

    public String getVirementPath() {
        return virementPath;
    }

    public void setVirementPath(String virementPath) {
        this.virementPath = virementPath;
    }

    public LocalDate getVirementDate() {
        return virementDate;
    }

    public void setVirementDate(LocalDate virementDate) {
        this.virementDate = virementDate;
    }

    public String getNomFournisseur() {
        return NomFournisseur;
    }

    public void setNomFournisseur(String NomFournisseur) {
        this.NomFournisseur = NomFournisseur;
    }
}

