package com.pfe.back.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomIng;

    @ElementCollection(fetch = FetchType.EAGER) 
    private List<String> articles; // A list of articles (String)

    @ElementCollection(fetch = FetchType.EAGER) 
    private List<Integer> quantites; // A list of quantities

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDemande = new Date(); // Date automatique

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomIng() { return nomIng; }
    public void setNomIng(String nomIng) { this.nomIng = nomIng; }

    public List<String> getArticles() { return articles; }
    public void setArticles(List<String> articles) { this.articles = articles; }

    public List<Integer> getQuantites() { return quantites; }
    public void setQuantites(List<Integer> quantites) { this.quantites = quantites; }

    public Date getDateDemande() { return dateDemande; }
    public void setDateDemande(Date dateDemande) { this.dateDemande = dateDemande; }
}
