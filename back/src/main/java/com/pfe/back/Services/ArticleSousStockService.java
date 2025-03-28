package com.pfe.back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.ArticleRepository;
import com.pfe.back.Repositories.ArticleSousStockRepository;
import com.pfe.back.Repositories.SubStockRepository;
import com.pfe.back.entities.Article;
import com.pfe.back.entities.ArticleSousStock;

@Service
public class ArticleSousStockService {

    

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SubStockRepository subStockRepository;

    @Autowired
    private ArticleSousStockRepository articleSousStockRepository;

    public ArticleSousStock createArticleSousStock(ArticleSousStock articleSousStock) {
        // Sauvegarde de l'article dans la base de données
        return articleSousStockRepository.save(articleSousStock);
    }
    
    
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    // Obtenir tous les articles dans un sous-stock spécifique
    public List<ArticleSousStock> getArticlesBySubStock(Long subStockId) {
        return articleSousStockRepository.findBySubStockId(subStockId);
    }

    public ArticleSousStock updateArticleSousStock(Long id, ArticleSousStock updatedArticle) {
        // Récupérer l'article existant de la base de données
        Optional<ArticleSousStock> articleOptional = articleSousStockRepository.findById(id);
    
        if (!articleOptional.isPresent()) {
            throw new RuntimeException("Article not found with id: " + id);
        }
    
        ArticleSousStock article = articleOptional.get();
    
        // Mettre à jour tous les champs
        article.setNom(updatedArticle.getNom());
        article.setDescription(updatedArticle.getDescription());
        article.setQuantiteDisponibleSousStock(updatedArticle.getQuantiteDisponibleSousStock());
        article.setPrixUnitaire(updatedArticle.getPrixUnitaire());
        article.setDateExpiration(updatedArticle.getDateExpiration());
        article.setReference(updatedArticle.getReference());
        article.setFournisseur(updatedArticle.getFournisseur());
        article.setSubStock(updatedArticle.getSubStock());  // Si tu veux aussi mettre à jour le sous-stock, sinon laisse-le comme il est.
    
        // Sauvegarder l'article mis à jour dans la base de données
        return articleSousStockRepository.save(article);
    }
    
    public void deleteArticleSousStock(Long articleSousStockId) {
        // Vérifier si l'ArticleSousStock existe dans le dépôt
        Optional<ArticleSousStock> articleSousStockOpt = articleSousStockRepository.findById(articleSousStockId);
    
        if (articleSousStockOpt.isPresent()) {
            // Supprimer l'ArticleSousStock
            articleSousStockRepository.delete(articleSousStockOpt.get());
        } else {
            throw new RuntimeException("ArticleSousStock non trouvé");
        }
    }
    
}
