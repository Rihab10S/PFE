package com.pfe.back.Services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.ArticleRepository;
import com.pfe.back.Repositories.StockPrincipalRepository;
import com.pfe.back.Repositories.SubStockRepository;
import com.pfe.back.entities.Article;
import com.pfe.back.entities.SubStock;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    private StockPrincipalRepository stockPrincipalRepository;

    @Autowired
    private SubStockRepository subStockRepository;


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
    public List<Article> getArticlesByStockPrincipal(Long stockPrincipalId) {
        return articleRepository.findByStockPrincipalId(stockPrincipalId);
    }

    public List<Article> getArticlesBySubStock(Long subStockId) {
        return articleRepository.findBySubStockId(subStockId);
    }
     // Déplacer un article du stock principal vers un sous-stock
    public Article moveArticleToSubStock(Long articleId, Long subStockId, int quantity) {
        Optional<Article> articleOpt = articleRepository.findById(articleId);
        Optional<SubStock> subStockOpt = subStockRepository.findById(subStockId);

        if (articleOpt.isPresent() && subStockOpt.isPresent()) {
            Article article = articleOpt.get();
            SubStock subStock = subStockOpt.get();

            // Vérifier si la quantité demandée est disponible dans le stock principal
            if (article.getQuantiteDisponible() >= quantity) {
                article.setQuantiteDisponible(article.getQuantiteDisponible() - quantity);
                articleRepository.save(article);

                // Ajouter l'article dans le sous-stock avec la nouvelle quantité
                Article subStockArticle = new Article();
                subStockArticle.setNom(article.getNom());
                subStockArticle.setDescription(article.getDescription());
                subStockArticle.setQuantiteDisponible(quantity);
                subStockArticle.setPrixUnitaire(article.getPrixUnitaire());
                subStockArticle.setDateExpiration(article.getDateExpiration());
                subStockArticle.setReference(article.getReference());
                subStockArticle.setFournisseur(article.getFournisseur());
                subStockArticle.setSubStock(subStock);
                subStockArticle.setStockPrincipal(article.getStockPrincipal());

                return articleRepository.save(subStockArticle);
            } else {
                throw new IllegalArgumentException("Quantité insuffisante dans le stock principal.");
            }
        }
        throw new NoSuchElementException("Article ou sous-stock non trouvé.");
    }
}