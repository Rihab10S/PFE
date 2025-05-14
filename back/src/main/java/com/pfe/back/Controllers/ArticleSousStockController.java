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
       

        ArticleSousStock createdArticle = articleSousStockService.createArticleSousStock(articleSousStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }

    @GetMapping("/sous-stock/{sousStockId}")
    public ResponseEntity<List<ArticleSousStock>> getArticlesBySousStock(@PathVariable Long sousStockId) {
        List<ArticleSousStock> articles = articleSousStockService.getArticlesBySousStock(sousStockId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleSousStock> updateArticleSousStock(
            @PathVariable Long id,
            @RequestBody ArticleSousStock updatedArticle) {
        try {
            ArticleSousStock article = articleSousStockService.updateArticleSousStock(id, updatedArticle);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

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
