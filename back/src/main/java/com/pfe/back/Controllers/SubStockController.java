package com.pfe.back.Controllers;



import com.pfe.back.Services.SubStockService;
import com.pfe.back.entities.SubStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sub-stock")
public class SubStockController {

    @Autowired
    private SubStockService subStockService;

    // Créer un nouveau SubStock
    @PostMapping
    public ResponseEntity<SubStock> createSubStock(@RequestBody SubStock subStock) {
        SubStock createdSubStock = subStockService.createSubStock(subStock);
        return ResponseEntity.ok(createdSubStock);
    }

    // Obtenir un SubStock par ID
    @GetMapping("/{id}")
    public ResponseEntity<SubStock> getSubStockById(@PathVariable Long id) {
        Optional<SubStock> subStock = subStockService.getSubStockById(id);
        return subStock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtenir tous les SubStocks
    @GetMapping
    public List<SubStock> getAllSubStocks() {
        return subStockService.getAllSubStocks();
    }

    // Mettre à jour un SubStock
    @PutMapping("/{id}")
    public ResponseEntity<SubStock> updateSubStock(@PathVariable Long id, @RequestBody SubStock subStock) {
        SubStock updatedSubStock = subStockService.updateSubStock(id, subStock);
        return updatedSubStock != null ? ResponseEntity.ok(updatedSubStock) : ResponseEntity.notFound().build();
    }

    // Supprimer un SubStock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubStock(@PathVariable Long id) {
        subStockService.deleteSubStock(id);
        return ResponseEntity.noContent().build();
    }
}
