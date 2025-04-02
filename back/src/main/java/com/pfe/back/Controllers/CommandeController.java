package com.pfe.back.Controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pfe.back.Repositories.CommandeRepository;
import com.pfe.back.Services.FileService;
import com.pfe.back.entities.Commande;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private FileService fileService;

    @PostMapping("/create")
    public Commande createCommande(
            @RequestParam("referencePersonne") String referencePersonne,
            @RequestParam("bonCommande") MultipartFile bonCommande,
            @RequestParam("facture") MultipartFile facture,
            @RequestParam("virement") MultipartFile virement,
            @RequestParam("bonReception") MultipartFile bonReception) throws IOException {

        // Créer l'entité Commande
        Commande commande = new Commande();
        commande.setReferencePersonne(referencePersonne);

        // Sauvegarder les fichiers et enregistrer les chemins dans la commande
        commande.setBonCommandePath(fileService.saveFile(bonCommande));
        commande.setFacturePath(fileService.saveFile(facture));
        commande.setVirementPath(fileService.saveFile(virement));
        commande.setBonReceptionPath(fileService.saveFile(bonReception));

         LocalDateTime now = LocalDateTime.now();
        commande.setBonCommandeDate(now.toLocalDate());
        commande.setFactureDate(now.toLocalDate());
        commande.setVirementDate(now.toLocalDate());
        commande.setBonReceptionDate(now.toLocalDate());

        // Sauvegarder la commande dans la base de données
        return commandeRepository.save(commande);
    }
    @DeleteMapping("/delete/{commandeId}")
    public ResponseEntity<String> deleteCommande(@PathVariable Long commandeId) {
        // Vérifier si la commande existe
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée pour l'ID : " + commandeId));

        try {
            // Supprimer les fichiers associés à la commande
            if (commande.getBonCommandePath() != null) {
                fileService.deleteFile(commande.getBonCommandePath());
            }
            if (commande.getFacturePath() != null) {
                fileService.deleteFile(commande.getFacturePath());
            }
            if (commande.getVirementPath() != null) {
                fileService.deleteFile(commande.getVirementPath());
            }
            if (commande.getBonReceptionPath() != null) {
                fileService.deleteFile(commande.getBonReceptionPath());
            }

            // Supprimer la commande de la base de données
            commandeRepository.deleteById(commandeId);
            
            return ResponseEntity.ok("Commande et fichiers supprimés avec succès");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de la commande ou des fichiers");
        }
    }

    @GetMapping
    public List<Commande> getAllMissions() {
        return commandeRepository.findAll();
    }
    
    
    

}
