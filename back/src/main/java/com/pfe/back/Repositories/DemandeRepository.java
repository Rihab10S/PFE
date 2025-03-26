package com.pfe.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.back.entities.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
