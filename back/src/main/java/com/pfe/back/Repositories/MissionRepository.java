package com.pfe.back.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.back.entities.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    Mission findByNomTech(String nomTech);

    List<Mission> findBySousStockId(Long num_sub_stock);
   

}
