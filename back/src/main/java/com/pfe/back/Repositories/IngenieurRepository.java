package com.pfe.back.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.entities.Ingenieur;

@Repository
public interface IngenieurRepository extends JpaRepository<Ingenieur, Long> {
    List<Ingenieur> findBySousStockId(Long sousStockId);  
}

