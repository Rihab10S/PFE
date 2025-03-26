package com.pfe.back.Services;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.IngenieurRepository;
import com.pfe.back.Repositories.SubStockRepository;
import com.pfe.back.entities.Ingenieur;
import com.pfe.back.entities.SubStock;



@Service
public class IngenieurService {

    @Autowired
    private IngenieurRepository ingenieurRepository;

    @Autowired
    private SubStockRepository subStockRepository;

    // Ajouter un ingénieur à un sous-stock
    public Ingenieur addIngenieurToSubStock(Ingenieur ingenieur, Long sousStockId) throws Throwable {
        SubStock sousStock = (SubStock) subStockRepository.findById(sousStockId)
                .orElseThrow(() -> new RelationNotFoundException("Sous-stock non trouvé"));

        ingenieur.setSousStock(sousStock);
        return ingenieurRepository.save(ingenieur);
    }

    // Afficher tous les ingénieurs d'un sous-stock
    public List<Ingenieur> getIngenieursBySubStock(Long sousStockId) {
        return ingenieurRepository.findBySousStockId(sousStockId);
    }

    // Modifier un ingénieur
    public Ingenieur updateIngenieur(Long id, Ingenieur ingenieurDetails) {
        Ingenieur ingenieur = ingenieurRepository.findById(id)
                .orElseThrow();

        ingenieur.setNom(ingenieurDetails.getNom());
        ingenieur.setPrenom(ingenieurDetails.getPrenom());
        ingenieur.setCin(ingenieurDetails.getCin());
        ingenieur.setTelephone(ingenieurDetails.getTelephone());

        return ingenieurRepository.save(ingenieur);
    }

    // Supprimer un ingénieur
    public void deleteIngenieur(Long id) {
        Ingenieur ingenieur = ingenieurRepository.findById(id)
                .orElseThrow();
        
        ingenieurRepository.delete(ingenieur);
    }
}
