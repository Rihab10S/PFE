package com.pfe.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.entities.FactVirement;

@Repository
public interface FactVirementRepository extends JpaRepository<FactVirement, Long> {
   
}

