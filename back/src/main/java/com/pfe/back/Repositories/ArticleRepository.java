package com.pfe.back.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.back.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByReference(String reference);
   
    // Trouver des articles par StockPrincipal
    List<Article> findByStockPrincipalId(Long stockPrincipalId);

    // Trouver des articles par SubStock
    List<Article> findBySubStockId(Long subStockId);
}