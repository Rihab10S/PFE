package com.pfe.back.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class StockPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresseStock;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stockPrincipal")
     @JsonManagedReference
    private List<Article> articles; // Articles dans le Stock Principal

    @OneToMany(mappedBy = "stockPrincipal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubStock> subStocks; // Sous Stocks liés au Stock Principal

    // Constructeur
    public StockPrincipal() {}

    public StockPrincipal(String nom, String adresseStock) {
        this.nom = nom;
        this.adresseStock = adresseStock;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseStock() {
        return adresseStock;
    }

    public void setAdresseStock(String adresseStock) {
        this.adresseStock = adresseStock;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<SubStock> getSubStocks() {
        return subStocks;
    }

    public void setSubStocks(List<SubStock> subStocks) {
        this.subStocks = subStocks;
    }
}
