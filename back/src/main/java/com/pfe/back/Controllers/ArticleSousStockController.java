package com.pfe.back.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.Services.ArticleSousStockService;
import com.pfe.back.entities.ArticleSousStock;
@RestController
@RequestMapping("/api/article-sous-stock")
public class ArticleSousStockController {

    @Autowired
    private ArticleSousStockService articleSousStockService;

    @PostMapping
    public ResponseEntity<ArticleSousStock> createArticleSousStock(@RequestBody ArticleSousStock articleSousStock) {
        // Assure-toi que l'article ne contient pas un id (auto-généré)
        articleSousStock.setId(null); // Si l'id est envoyé, on le met à null pour laisser la DB l'auto-générer
        ArticleSousStock createdArticle = articleSousStockService.createArticleSousStock(articleSousStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }



    // Obtenir tous les articles dans un sous-stock spécifique
    @GetMapping("/substock/{subStockId}")
    public ResponseEntity<List<ArticleSousStock>> getArticlesBySubStock(@PathVariable Long subStockId) {
        List<ArticleSousStock> articles = articleSousStockService.getArticlesBySubStock(subStockId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleSousStock> updateArticleSousStock(
            @PathVariable Long id,
            @RequestBody ArticleSousStock updatedArticle) {  // Reçoit l'objet complet à mettre à jour

        try {
            // Appel du service pour mettre à jour tous les champs de l'article
            ArticleSousStock article = articleSousStockService.updateArticleSousStock(id, updatedArticle);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    // Supprimer un article dans un sous-stock
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticleSousStock(@PathVariable Long id) {
        try {
            articleSousStockService.deleteArticleSousStock(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
