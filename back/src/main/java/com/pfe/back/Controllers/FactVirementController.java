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

import com.pfe.back.Repositories.FactVirementRepository;
import com.pfe.back.Services.FileFactVirementService;
import com.pfe.back.entities.FactVirement;

@RestController
@RequestMapping("/FactVirement")
public class FactVirementController {
    
    @Autowired
    private FactVirementRepository factVirementRepository;

    @Autowired
    private FileFactVirementService fileService;

    @PostMapping("/create")
    public FactVirement createFactVirement(
            @RequestParam("nomFournisseur") String nomFournisseur,
            @RequestParam("facture") MultipartFile facture,
            @RequestParam("virement") MultipartFile virement) throws IOException {

        // Créer l'entité FactVirement
        FactVirement factVirement = new FactVirement();
        factVirement.setNomFournisseur(nomFournisseur);

        // Sauvegarder les fichiers et enregistrer les chemins
        factVirement.setFacturePath(fileService.saveFile(facture));
        factVirement.setVirementPath(fileService.saveFile(virement));

        // Enregistrer la date de création
        LocalDateTime now = LocalDateTime.now();
        factVirement.setFactureDate(now.toLocalDate());
        factVirement.setVirementDate(now.toLocalDate());

        // Sauvegarder dans la base de données
        return factVirementRepository.save(factVirement);
    }

    @DeleteMapping("/delete/{factVirementId}")
    public ResponseEntity<String> deleteFactVirement(@PathVariable Long factVirementId) {
        // Vérifier si l'entité existe
        FactVirement factVirement = factVirementRepository.findById(factVirementId)
                .orElseThrow(() -> new RuntimeException("FactVirement non trouvé pour l'ID : " + factVirementId));

        try {
            // Supprimer les fichiers associés
            if (factVirement.getFacturePath() != null) {
                fileService.deleteFile(factVirement.getFacturePath());
            }
            if (factVirement.getVirementPath() != null) {
                fileService.deleteFile(factVirement.getVirementPath());
            }

            // Supprimer l'entité de la base de données
            factVirementRepository.deleteById(factVirementId);
            
            return ResponseEntity.ok("FactVirement et fichiers supprimés avec succès");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de FactVirement ou des fichiers");
        }
    }

    @GetMapping
    public List<FactVirement> getAllFactVirements() {
        return factVirementRepository.findAll();
    }
}
