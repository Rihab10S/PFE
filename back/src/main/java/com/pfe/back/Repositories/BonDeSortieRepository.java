package com.pfe.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.entities.BonDeSortie;

@Repository
public interface BonDeSortieRepository extends JpaRepository<BonDeSortie, Long> {
}
