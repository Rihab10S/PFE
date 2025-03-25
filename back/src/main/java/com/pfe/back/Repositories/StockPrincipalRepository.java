package com.pfe.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.back.entities.StockPrincipal;

public interface StockPrincipalRepository extends JpaRepository<StockPrincipal, Long> {
    
}
