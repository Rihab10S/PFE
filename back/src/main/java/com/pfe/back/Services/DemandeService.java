package com.pfe.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.DemandeRepository;
import com.pfe.back.entities.Demande;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    // 🔹 Créer une demande
    public Demande creerDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    // 🔹 Afficher toutes les demandes
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    // 🔹 Modifier une demande
    public Demande modifierDemande(Long id, Demande newDemande) {
        return demandeRepository.findById(id).map(demande -> {
            demande.setNomIng(newDemande.getNomIng());
            demande.setArticles(newDemande.getArticles());
            demande.setQuantites(newDemande.getQuantites());
            demande.setDateDemande(newDemande.getDateDemande());
            return demandeRepository.save(demande);
        }).orElseThrow(() -> new RuntimeException("Demande non trouvée"));
    }

    // 🔹 Supprimer une demande
    public void supprimerDemande(Long id) {
        demandeRepository.deleteById(id);
    }
}
