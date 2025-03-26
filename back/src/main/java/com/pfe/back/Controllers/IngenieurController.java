package com.pfe.back.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.Services.IngenieurService;
import com.pfe.back.entities.Ingenieur;

@RestController
@RequestMapping("/api/sous-stock/{sousStockId}/ingenieurs")
public class IngenieurController {

    @Autowired
    private IngenieurService ingenieurService;

    // Ajouter un ingénieur
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Ingenieur> addIngenieurToSubStock(@PathVariable Long sousStockId, @RequestBody Ingenieur ingenieur) throws Throwable {
        Ingenieur newIngenieur = ingenieurService.addIngenieurToSubStock(ingenieur, sousStockId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newIngenieur);
    }

    // Obtenir tous les ingénieurs d'un sous-stock
    @GetMapping
    public List<Ingenieur> getIngenieursBySubStock(@PathVariable Long sousStockId) {
        return ingenieurService.getIngenieursBySubStock(sousStockId);
    }

    // Modifier un ingénieur
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Ingenieur> updateIngenieur(@PathVariable Long id, @RequestBody Ingenieur ingenieurDetails) {
        Ingenieur updatedIngenieur = ingenieurService.updateIngenieur(id, ingenieurDetails);
        return ResponseEntity.ok(updatedIngenieur);
    }

    // Supprimer un ingénieur
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteIngenieur(@PathVariable Long id) {
        ingenieurService.deleteIngenieur(id);
        return ResponseEntity.noContent().build();
    }
}
