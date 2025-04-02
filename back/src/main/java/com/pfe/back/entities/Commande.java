package com.pfe.back.entities;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Commande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NomFournisseur")
    private String referencePersonne; 
    
    private String bonCommandePath; 
    private LocalDate bonCommandeDate;

    private String facturePath; 
    private LocalDate factureDate;

    private String virementPath; 
    private LocalDate virementDate;

    private String bonReceptionPath; 
    private LocalDate bonReceptionDate;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBonCommandePath() {
        return bonCommandePath;
    }

    public void setBonCommandePath(String bonCommandePath) {
        this.bonCommandePath = bonCommandePath;
    }

    public LocalDate getBonCommandeDate() {
        return bonCommandeDate;
    }

    public void setBonCommandeDate(LocalDate bonCommandeDate) {
        this.bonCommandeDate = bonCommandeDate;
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

    public String getBonReceptionPath() {
        return bonReceptionPath;
    }

    public void setBonReceptionPath(String bonReceptionPath) {
        this.bonReceptionPath = bonReceptionPath;
    }

    public LocalDate getBonReceptionDate() {
        return bonReceptionDate;
    }

    public void setBonReceptionDate(LocalDate bonReceptionDate) {
        this.bonReceptionDate = bonReceptionDate;
    }

   

    public String getReferencePersonne() {
        return referencePersonne;
    }

    public void setReferencePersonne(String referencePersonne) {
        this.referencePersonne = referencePersonne;
    }
}
