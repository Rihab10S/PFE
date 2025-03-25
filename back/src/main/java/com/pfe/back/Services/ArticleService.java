package com.pfe.back.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.ArticleRepository;
import com.pfe.back.entities.Article;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article articleDetails) {
        return articleRepository.findById(id).map(article -> {
            article.setNom(articleDetails.getNom());
            article.setDescription(articleDetails.getDescription());
            article.setPrixUnitaire(articleDetails.getPrixUnitaire());
            article.setQuantiteDisponible(articleDetails.getQuantiteDisponible());
            article.setReference(articleDetails.getReference());
            article.setFournisseur(articleDetails.getFournisseur());
            return articleRepository.save(article);
        }).orElseThrow(() -> new RuntimeException("Article non trouvé"));
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
    public boolean existsByReference(String reference) {
        return articleRepository.existsByReference(reference);
    }
}