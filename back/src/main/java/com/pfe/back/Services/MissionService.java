package com.pfe.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.ArticleRepository;
import com.pfe.back.Repositories.ArticleSousStockRepository;
import com.pfe.back.Repositories.MissionRepository;
import com.pfe.back.entities.ArticleMission;
import com.pfe.back.entities.ArticleSousStock;
import com.pfe.back.entities.Mission;

import jakarta.transaction.Transactional;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private ArticleSousStockRepository articleSousStockRepository;
    @Autowired
    private ArticleRepository articleRepository;

        // Créer une mission avec des articles
            @Transactional
            public Mission createMission(Mission mission) {
                // Parcourir les articles de la mission
                for (ArticleMission articleMission : mission.getArticles()) {
                    Long articleId = articleMission.getArticle().getId();

                    // Charger l'article depuis la base de données
                    ArticleSousStock article = articleSousStockRepository.findById(articleId)
                            .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID : " + articleId));

                    // Vérifier la quantité demandée
                    if (articleMission.getQuantite() <= 0) {
                        throw new RuntimeException("La quantité demandée pour l'article " + article.getNom() + " est invalide.");
                    }

                    // Associer l'article récupéré à ArticleMission
                    articleMission.setArticle(article);
                }

                // Enregistrer et retourner la mission
                return missionRepository.save(mission);
            }




    // Afficher toutes les missions
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }
    // Récupérer les missions par numéro de sous-stock
    public List<Mission> getMissionsBySousStock(Long numeroSousStock) {
        return missionRepository.findBySousStockId(numeroSousStock);
    }

    public Mission getMissionByNomTech(String nomTech) {
        // Recherche de la mission par nom de technicien
        return missionRepository.findByNomTech(nomTech);
    }
    

    // Afficher une mission par ID
  @Transactional
        public Mission getMissionById(Long id) {
            Mission mission = missionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Mission non trouvée"));

            // Forcer le chargement des articles (si en Lazy)
            mission.getArticles().forEach(articleMission -> {
                articleMission.getArticle().getNom(); // Accès pour charger l'entité
            });

            return mission;
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

    @Transactional
    public void validerMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission introuvable"));

        if (!mission.getStatut().equalsIgnoreCase("valider")) {
            mission.setStatut("valider");

            // Parcourir les articles liés à la mission
            for (ArticleMission articleMission : mission.getArticles()) {
                ArticleSousStock article = articleMission.getArticle();

                // Chercher l'articleSousStock correspondant (par id)
                ArticleSousStock sousStock = articleSousStockRepository
                        .findById(article.getId())
                        .orElseThrow(() -> new RuntimeException("Article sous-stock non trouvé"));

                // Diminuer la quantité
                int nouvelleQuantite = sousStock.getQuantiteDisponibleSousStock() - articleMission.getQuantite();

                if (nouvelleQuantite < 0) {
                    throw new RuntimeException("Quantité insuffisante pour l'article " + sousStock.getNom());
                }

                sousStock.setQuantiteDisponibleSousStock(nouvelleQuantite);
                articleSousStockRepository.save(sousStock);
            }

            // Sauvegarder la mission validée
            missionRepository.save(mission);
        } else {
            throw new RuntimeException("Mission déjà validée");
        }
    }

    }
