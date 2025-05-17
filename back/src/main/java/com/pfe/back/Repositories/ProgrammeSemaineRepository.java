package com.pfe.back.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.back.entities.ProgrammeSemaine;

public interface ProgrammeSemaineRepository extends JpaRepository<ProgrammeSemaine, Long> {
      List<ProgrammeSemaine> findBySousStockId(Long sousStockId);
}
