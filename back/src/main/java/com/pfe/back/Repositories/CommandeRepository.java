package com.pfe.back.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.entities.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByReferencePersonne(String referencePersonne);
}
