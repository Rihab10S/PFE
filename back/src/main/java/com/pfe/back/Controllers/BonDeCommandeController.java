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

import com.pfe.back.Services.BonDeCommandeService;
import com.pfe.back.entities.BonDeCommande;

@RestController
@RequestMapping("/api/bons-de-commande")
public class BonDeCommandeController {

    @Autowired
    private BonDeCommandeService bonDeCommandeService;

    @GetMapping
    public List<BonDeCommande> getAllBonsDeCommande() {
        return bonDeCommandeService.getAllBonsDeCommande();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonDeCommande> getBonDeCommandeById(@PathVariable Long id) {
        return ResponseEntity.ok(bonDeCommandeService.getBonDeCommandeById(id));
    }

    @PostMapping
    public ResponseEntity<BonDeCommande> createBonDeCommande(@RequestBody BonDeCommande bonDeCommande) {
        return new ResponseEntity<>(bonDeCommandeService.createBonDeCommande(bonDeCommande), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonDeCommande> updateBonDeCommande(
            @PathVariable Long id, 
            @RequestBody BonDeCommande updatedBonDeCommande) {
        return ResponseEntity.ok(bonDeCommandeService.updateBonDeCommande(id, updatedBonDeCommande));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonDeCommande(@PathVariable Long id) {
        bonDeCommandeService.deleteBonDeCommande(id);
        return ResponseEntity.noContent().build();
    }
}
