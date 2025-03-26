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
public class BonDeSortie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomIng; // Nom de l'ingénieur
    private LocalDate dateSortie; // Date de sortie

    @OneToMany(mappedBy = "bonDeSortie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    
    private List<BonDeSortieArticle> articles = new ArrayList<>();

    public BonDeSortie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getNomIng() {
        return nomIng;
    }

    public void setNomIng(String nomIng) {
        this.nomIng = nomIng;
    }

    public List<BonDeSortieArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<BonDeSortieArticle> articles) {
        this.articles = articles;
    }

}
