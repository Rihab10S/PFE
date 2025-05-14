package com.pfe.back.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomTech;
    private String description;
    private LocalDate date;
    private String lieu;
    private String statut;
    private Long sousStockId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JoinColumn(name = "mission_id") // relation bidirectionnelle avec l'article
    private List<ArticleMission> articles;

    // Getters et Setters

   

    public String getNomTech() {
        return nomTech;
    }

    public void setNomTech(String nomTech) {
        this.nomTech = nomTech;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ArticleMission> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleMission> articles) {
        this.articles = articles;
    }

    public Long getSousStockId() {
        return sousStockId;
    }

    public void setSousStockId(Long sousStockId) {
        this.sousStockId = sousStockId;
    }

    
}
