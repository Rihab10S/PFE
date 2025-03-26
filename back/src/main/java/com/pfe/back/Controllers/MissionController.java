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

import com.pfe.back.Services.MissionService;
import com.pfe.back.entities.Mission;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    // Créer une mission
    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission createdMission = missionService.createMission(mission);
        return new ResponseEntity<>(createdMission, HttpStatus.CREATED);
    }

    // Afficher toutes les missions
    @GetMapping
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    // Afficher une mission par ID
    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMissionById(@PathVariable Long id) {
        Mission mission = missionService.getMissionById(id);
        return new ResponseEntity<>(mission, HttpStatus.OK);
    }
    // Afficher une mission par Nom du Technicien
    @GetMapping("/technicien/{nomTechnicien}")
    public ResponseEntity<Mission> getMissionByNomTechnicien(@PathVariable String nomTechnicien) {
        Mission mission = missionService.getMissionByNomTech(nomTechnicien);
        
        // Vérifier si la mission existe
        if (mission != null) {
            return new ResponseEntity<>(mission, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retourner 404 si aucune mission n'est trouvée
        }
    }



    // Modifier une mission
    @PutMapping("/{id}")
    public ResponseEntity<Mission> updateMission(@PathVariable Long id, @RequestBody Mission missionDetails) {
        Mission updatedMission = missionService.updateMission(id, missionDetails);
        return new ResponseEntity<>(updatedMission, HttpStatus.OK);
    }

    // Supprimer une mission
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
