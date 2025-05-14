package com.pfe.back.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.back.entities.ArticleSousStock;

public interface ArticleSousStockRepository extends JpaRepository<ArticleSousStock, Long> {
    List<ArticleSousStock> findBySousStockId(Long sousStockId);
    Optional<ArticleSousStock> findByReference(String reference);

}
