package com.pfe.back.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.CommandeRepository;
import com.pfe.back.entities.Commande;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public List<Commande> getCommandesParPersonne(String referencePersonne) {
        return commandeRepository.findByReferencePersonne(referencePersonne);
    }

    public Commande addCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Long id, Commande newCommande) {
        return commandeRepository.findById(id).map(commande -> {
            commande.setReferencePersonne(newCommande.getReferencePersonne());
            
            commande.setBonCommandePath(newCommande.getBonCommandePath());
            commande.setBonCommandeDate(newCommande.getBonCommandeDate());
            commande.setFacturePath(newCommande.getFacturePath());
            commande.setFactureDate(newCommande.getFactureDate());
            commande.setVirementPath(newCommande.getVirementPath());
            commande.setVirementDate(newCommande.getVirementDate());
            commande.setBonReceptionPath(newCommande.getBonReceptionPath());
            commande.setBonReceptionDate(newCommande.getBonReceptionDate());
            return commandeRepository.save(commande);
        }).orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}
