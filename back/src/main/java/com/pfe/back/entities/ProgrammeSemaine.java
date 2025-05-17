package com.pfe.back.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgrammeSemaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String mission;

    private String nomTech;

    private LocalDate date;

    private String statut;
     private Long sousStockId;

    // Getters et Setters

    

    public String getNomTech() {
        return nomTech;
    }

    public void setNomTech(String nomTech) {
        this.nomTech = nomTech;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public Long getSousStockId() {
        return sousStockId;
    }

    public void setSousStockId(Long sousStockId) {
        this.sousStockId = sousStockId;
    }
}
