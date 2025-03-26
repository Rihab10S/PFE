package com.pfe.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.BonDeCommandeRepository;
import com.pfe.back.entities.ArticleCommande;
import com.pfe.back.entities.BonDeCommande;

@Service
public class BonDeCommandeService {

    @Autowired
    private BonDeCommandeRepository bonDeCommandeRepository;

    public List<BonDeCommande> getAllBonsDeCommande() {
        return bonDeCommandeRepository.findAll();
    }

    public BonDeCommande getBonDeCommandeById(Long id) {
        return bonDeCommandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bon de commande introuvable !"));
    }

    public BonDeCommande createBonDeCommande(BonDeCommande bonDeCommande) {
        // Calcul du prix total et quantité totale
        int totalQuantite = 0;
        double totalPrix = 0.0;
        
        for (ArticleCommande article : bonDeCommande.getArticles()) {
            totalQuantite += article.getQuantite();
            totalPrix += article.getQuantite() * article.getPrixUnitaire();
            article.setBonDeCommande(bonDeCommande);
        }

        bonDeCommande.setQuantiteTotale(totalQuantite);
        bonDeCommande.setPrixTotal(totalPrix);

        return bonDeCommandeRepository.save(bonDeCommande);
    }

    public BonDeCommande updateBonDeCommande(Long id, BonDeCommande updatedBonDeCommande) {
        BonDeCommande existing = getBonDeCommandeById(id);
        existing.setFournisseur(updatedBonDeCommande.getFournisseur());
        existing.setDateAuPlusTard(updatedBonDeCommande.getDateAuPlusTard());
        existing.setDateCommande(updatedBonDeCommande.getDateCommande());
        existing.setStatut(updatedBonDeCommande.isStatut());

        // Mise à jour des articles
        existing.getArticles().clear();
        for (ArticleCommande article : updatedBonDeCommande.getArticles()) {
            article.setBonDeCommande(existing);
            existing.getArticles().add(article);
        }

        return bonDeCommandeRepository.save(existing);
    }

    public void deleteBonDeCommande(Long id) {
        bonDeCommandeRepository.deleteById(id);
    }
}
