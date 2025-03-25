package com.pfe.back.Controllers;



import com.pfe.back.Services.StockPrincipalService;
import com.pfe.back.entities.StockPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock-principal")
public class StockPrincipalController {

    @Autowired
    private StockPrincipalService stockPrincipalService;

    // Créer un nouveau StockPrincipal
    @PostMapping
    public ResponseEntity<StockPrincipal> createStockPrincipal(@RequestBody StockPrincipal stockPrincipal) {
        StockPrincipal createdStock = stockPrincipalService.createStockPrincipal(stockPrincipal);
        return ResponseEntity.ok(createdStock);
    }

   

    // Obtenir tous les StockPrincipals
    @GetMapping
    public List<StockPrincipal> getAllStockPrincipals() {
        return stockPrincipalService.getAllStockPrincipals();
    }

    // Mettre à jour un StockPrincipal
    @PutMapping("/{id}")
    public ResponseEntity<StockPrincipal> updateStockPrincipal(@PathVariable Long id, @RequestBody StockPrincipal stockPrincipal) {
        StockPrincipal updatedStock = stockPrincipalService.updateStockPrincipal(id, stockPrincipal);
        return updatedStock != null ? ResponseEntity.ok(updatedStock) : ResponseEntity.notFound().build();
    }

    // Supprimer un StockPrincipal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockPrincipal(@PathVariable Long id) {
        stockPrincipalService.deleteStockPrincipal(id);
        return ResponseEntity.noContent().build();
    }
}
