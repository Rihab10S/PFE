package com.pfe.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.ArticleRepository;
import com.pfe.back.Repositories.BonDeSortieRepository;
import com.pfe.back.entities.Article;
import com.pfe.back.entities.BonDeSortie;
import com.pfe.back.entities.BonDeSortieArticle;

import jakarta.transaction.Transactional;

@Service
public class BonDeSortieService {

    @Autowired
    private BonDeSortieRepository bonDeSortieRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public BonDeSortie createBonDeSortie(BonDeSortie bonDeSortie, List<BonDeSortieArticle> articles) {
        for (BonDeSortieArticle article : articles) {
            Article existingArticle = articleRepository.findById(article.getArticle().getId())
                    .orElseThrow(() -> new RuntimeException("Article introuvable"));

            if (article.getQuantite() > existingArticle.getQuantiteDisponible()) {
                throw new RuntimeException("Quantité demandée non disponible");
            }

            existingArticle.setQuantiteDisponible(existingArticle.getQuantiteDisponible() - article.getQuantite());
            article.setPrixTotal(article.getQuantite() * existingArticle.getPrixUnitaire());
            article.setBonDeSortie(bonDeSortie);
        }

        bonDeSortie.setArticles(articles);
        return bonDeSortieRepository.save(bonDeSortie);
    }
    @Transactional
    public List<BonDeSortie> getAllBonsDeSortie() {
        return bonDeSortieRepository.findAll();
    }
    @Transactional
    public BonDeSortie getBonDeSortieById(Long id) {
        return bonDeSortieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bon de sortie non trouvé"));
    }

    public BonDeSortie updateBonDeSortie(Long id, BonDeSortie updatedBon) {
        BonDeSortie existingBon = getBonDeSortieById(id);
        existingBon.setNomIng(updatedBon.getNomIng());
        existingBon.setDateSortie(updatedBon.getDateSortie());
        return bonDeSortieRepository.save(existingBon);
    }

    public void deleteBonDeSortie(Long id) {
        bonDeSortieRepository.deleteById(id);
    }
}
