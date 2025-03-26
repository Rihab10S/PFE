package com.pfe.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.entities.BonDeCommande;

@Repository
public interface BonDeCommandeRepository extends JpaRepository<BonDeCommande, Long> {
}