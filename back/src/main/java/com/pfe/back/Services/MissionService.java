package com.pfe.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.ArticleRepository;
import com.pfe.back.Repositories.MissionRepository;
import com.pfe.back.entities.Mission;

import jakarta.transaction.Transactional;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private ArticleRepository articleRepository;

    // Créer une mission avec des articles
    @Transactional
    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    // Afficher toutes les missions
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public Mission getMissionByNomTech(String nomTech) {
        // Recherche de la mission par nom de technicien
        return missionRepository.findByNomTech(nomTech);
    }
    

    // Afficher une mission par ID
    public Mission getMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission non trouvée"));
    }

    // Modifier une mission
    @Transactional
    public Mission updateMission(Long id, Mission missionDetails) {
        Mission mission = getMissionById(id);
        mission.setNomTech(missionDetails.getNomTech());
        mission.setDescription(missionDetails.getDescription());
        mission.setDate(missionDetails.getDate());
        mission.setLieu(missionDetails.getLieu());
        mission.setStatut(missionDetails.getStatut());
        return missionRepository.save(mission);
    }

    // Supprimer une mission
    @Transactional
    public void deleteMission(Long id) {
        Mission mission = getMissionById(id);
        missionRepository.delete(mission);
    }
}
