package com.pfe.back.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.Services.BonDeSortieService;
import com.pfe.back.entities.BonDeSortie;

@RestController
@RequestMapping("/api/bon-de-sortie")
public class BonDeSortieController {

    @Autowired
    private BonDeSortieService bonDeSortieService;


    @PostMapping
    public ResponseEntity<BonDeSortie> createBonDeSortie(@RequestBody BonDeSortie bonDeSortie) {
        BonDeSortie newBon = bonDeSortieService.createBonDeSortie(bonDeSortie, bonDeSortie.getArticles());
        return ResponseEntity.ok(newBon);
    }

    @GetMapping
    public ResponseEntity<List<BonDeSortie>> getAllBonsDeSortie() {
        return ResponseEntity.ok(bonDeSortieService.getAllBonsDeSortie());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonDeSortie> getBonDeSortieById(@PathVariable Long id) {
        return ResponseEntity.ok(bonDeSortieService.getBonDeSortieById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonDeSortie> updateBonDeSortie(@PathVariable Long id, @RequestBody BonDeSortie bonDeSortie) {
        return ResponseEntity.ok(bonDeSortieService.updateBonDeSortie(id, bonDeSortie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonDeSortie(@PathVariable Long id) {
        bonDeSortieService.deleteBonDeSortie(id);
        return ResponseEntity.noContent().build();
    }
}
