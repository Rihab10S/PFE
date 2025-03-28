package com.pfe.back.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class SubStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomSousStock;

    private String nomIngRespo; // Nom de l'ingénieur responsable

    @ManyToOne
    @JsonIgnore 
    @JoinColumn(name = "stock_principal_id")
    private StockPrincipal stockPrincipal;// Relation avec StockPrincipal


    @OneToMany(mappedBy = "subStock", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore 
    private List<ArticleSousStock> articleSousStocks;



    @OneToMany(mappedBy = "subStock")  
    @JsonIgnore 
    private List<Article> articles;// Articles transférés du Stock Principal vers le Sous Stock
    // Constructeurs
    public SubStock() {}

    public SubStock(String nomSousStock, String nomIngRespo) {
        this.nomSousStock = nomSousStock;
        this.nomIngRespo = nomIngRespo;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomSousStock() {
        return nomSousStock;
    }

    public void setNomSousStock(String nomSousStock) {
        this.nomSousStock = nomSousStock;
    }

    public String getNomIngRespo() {
        return nomIngRespo;
    }

    public void setNomIngRespo(String nomIngRespo) {
        this.nomIngRespo = nomIngRespo;
    }
    public StockPrincipal getStockPrincipal() {
        return stockPrincipal;
    }

    public void setStockPrincipal(StockPrincipal stockPrincipal) {
        this.stockPrincipal = stockPrincipal;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<ArticleSousStock> getArticleSousStocks() {
        return articleSousStocks;
    }

    public void setArticleSousStocks(List<ArticleSousStock> articleSousStocks) {
        this.articleSousStocks = articleSousStocks;
    }

}
