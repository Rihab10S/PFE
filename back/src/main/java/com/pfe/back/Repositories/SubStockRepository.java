package com.pfe.back.Repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.back.entities.SubStock;

public interface SubStockRepository extends JpaRepository<SubStock, Long> {
    Optional<SubStock> findById(Long id); 
    Optional<SubStock> findByNomSousStock(String nomSousStock);

}

