package com.pfe.back.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.StockPrincipalRepository;
import com.pfe.back.entities.Article;
import com.pfe.back.entities.StockPrincipal;

@Service
public class StockPrincipalService {

    @Autowired
    private StockPrincipalRepository stockPrincipalRepository;

    // Créer un nouveau StockPrincipal
    public StockPrincipal createStockPrincipal(StockPrincipal stockPrincipal) {
        return stockPrincipalRepository.save(stockPrincipal);
    }


    // Obtenir tous les StockPrincipal
    public List<StockPrincipal> getAllStockPrincipals() {
        return stockPrincipalRepository.findAll();
    }

    // Mettre à jour un StockPrincipal
    public StockPrincipal updateStockPrincipal(Long id, StockPrincipal stockPrincipal) {
        if (stockPrincipalRepository.existsById(id)) {
            stockPrincipal.setId(id); // Assurez-vous de maintenir l'ID
            return stockPrincipalRepository.save(stockPrincipal);
        }
        return null;
    }

    // Supprimer un StockPrincipal
    public void deleteStockPrincipal(Long id) {
        stockPrincipalRepository.deleteById(id);
    }
    public Optional<StockPrincipal> getStockPrincipalById(Long id) {
        return stockPrincipalRepository.findById(id);
    }


    public static Optional<Article> findById(Integer stockPrincipalId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
