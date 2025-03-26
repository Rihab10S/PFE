package com.pfe.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.entities.BonDeSortieArticle;

@Repository
public interface BonDeSortieArticleRepository extends JpaRepository<BonDeSortieArticle, Long> {
}
