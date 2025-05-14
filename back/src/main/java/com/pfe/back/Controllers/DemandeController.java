package com.pfe.back.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.Services.DemandeService;
import com.pfe.back.entities.Demande;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    //  Créer une demande
    @PostMapping
    public Demande creerDemande(@RequestBody Demande demande) {
        return demandeService.creerDemande(demande);
    }

    // Afficher toutes les demandes
    @GetMapping
    public List<Demande> getAllDemandes() {
        return demandeService.getAllDemandes();
    }
     // Récupérer une demande par ID
    @GetMapping("/{id}")
    public Demande getDemandeById(@PathVariable Long id) {
        Optional<Demande> demande = demandeService.getDemandeById(id);
        if (demande.isPresent()) {
            return demande.get();  // Renvoie la demande trouvée
        } else {
            throw new RuntimeException("Demande non trouvée pour l'ID " + id);  // Erreur si la demande n'est pas trouvée
        }
    }

    //  Modifier une demande
    @PutMapping("/{id}")
    public Demande modifierDemande(@PathVariable Long id, @RequestBody Demande newDemande) {
        return demandeService.modifierDemande(id, newDemande);
    }

    // Supprimer une demande
    @DeleteMapping("/{id}")
    public void supprimerDemande(@PathVariable Long id) {
        demandeService.supprimerDemande(id);
    }
}
