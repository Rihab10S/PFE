package com.pfe.back.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom") 
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "quantite_disponible")
    private int quantiteDisponible;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;

    @Column(name = "date_expiration")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE) 
    private Date dateExpiration;

    @Column(name = "reference")
    private String reference;

    @Column(name = "fournisseur")
    private String fournisseur;

    @ManyToOne
    @JoinColumn(name = "stock_principal_id")
    @JsonBackReference
    @JsonIgnore
    private StockPrincipal stockPrincipal; // Relation avec StockPrincipal

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sub_stock_id")
    private SubStock subStock; // Relation avec SousStock (si l'article est transféré)

    // Constructeur par défaut
    public Article() {}

    // Constructeur avec paramètres pour créer un nouvel Article
    public Article(String nom, String description, Double prixUnitaire, int quantiteDisponible, 
                   Date dateExpiration, String reference, String fournisseur, StockPrincipal stockPrincipal, SubStock subStock) {
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.quantiteDisponible = quantiteDisponible;
        this.dateExpiration = dateExpiration;
        this.reference = reference;
        this.fournisseur = fournisseur;
        this.stockPrincipal = stockPrincipal;
        this.subStock = subStock;
    }

    public Article(String nom, String description, Double prixUnitaire, int quantiteDisponible, 
               Date dateExpiration, String reference, String fournisseur, Integer stockPrincipalId, Integer subStockId) {
    this.nom = nom;
    this.description = description;
    this.prixUnitaire = prixUnitaire;
    this.quantiteDisponible = quantiteDisponible;
    this.dateExpiration = dateExpiration;
    this.reference = reference;
    this.fournisseur = fournisseur;
    this.stockPrincipal = stockPrincipalId != null ? new StockPrincipal() : null;
    this.subStock = subStockId != null ? new SubStock() : null;
}

    // Constructeur avec ID (utile pour récupérer un article existant)
    public Article(long id, String nom, String description, Double prixUnitaire, int quantiteDisponible,
                   Date dateExpiration, String reference, String fournisseur, StockPrincipal stockPrincipal, SubStock subStock) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.quantiteDisponible = quantiteDisponible;
        this.dateExpiration = dateExpiration;
        this.reference = reference;
        this.fournisseur = fournisseur;
        this.stockPrincipal = stockPrincipal;
        this.subStock = subStock;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
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

    public StockPrincipal getStockPrincipal() {
        return stockPrincipal;
    }

    public void setStockPrincipal(StockPrincipal stockPrincipal) {
        this.stockPrincipal = stockPrincipal;
    }

    public SubStock getSubStock() {
        return subStock;
    }

    public void setSubStock(SubStock subStock) {
        this.subStock = subStock;
    }
}
