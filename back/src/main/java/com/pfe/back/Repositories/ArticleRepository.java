package com.pfe.back.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.back.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByReference(String reference);
}