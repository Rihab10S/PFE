package com.pfe.back.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.SubStockRepository;
import com.pfe.back.entities.Article;
import com.pfe.back.entities.SubStock;

import jakarta.transaction.Transactional;

@Service
public class SubStockService {

    @Autowired
    private SubStockRepository subStockRepository;

    // Créer un nouveau SubStock
    public SubStock createSubStock(SubStock subStock) {
        return subStockRepository.save(subStock);
    }
    @Transactional
    // Obtenir un SubStock par son ID
    public Optional<SubStock> getSubStockById(Long id) {
        return subStockRepository.findById(id);
    }

    // Obtenir tous les SubStock
    public List<SubStock> getAllSubStocks() {
        return subStockRepository.findAll();
    }

    // Mettre à jour un SubStock
    public SubStock updateSubStock(Long id, SubStock subStock) {
        if (subStockRepository.existsById(id)) {
            subStock.setId(id); // Assurez-vous de maintenir l'ID
            return subStockRepository.save(subStock);
        }
        return null;
    }

    // Supprimer un SubStock
    public void deleteSubStock(Long id) {
        subStockRepository.deleteById(id);
    }

    public static Optional<Article> findById(Integer sousStockId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
