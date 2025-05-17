package com.pfe.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.ProgrammeSemaineRepository;
import com.pfe.back.entities.ProgrammeSemaine;

@Service
public class ProgrammeSemaineService {

    @Autowired
    private ProgrammeSemaineRepository repository;

    public ProgrammeSemaine addProgramme(ProgrammeSemaine programme) {
        return repository.save(programme);
    }

    public List<ProgrammeSemaine> getAllProgrammes() {
        return repository.findAll();
    }
    public ProgrammeSemaine getProgrammeById(Long id) {
        return repository.findById(id).orElse(null);
    }
    

    public ProgrammeSemaine updateProgramme(Long id, ProgrammeSemaine updatedProgramme) {
        return repository.findById(id).map(p -> {
            p.setMission(updatedProgramme.getMission());
            p.setNomTech(updatedProgramme.getNomTech());
            p.setDate(updatedProgramme.getDate());
            p.setStatut(updatedProgramme.getStatut());
              p.setSousStockId(updatedProgramme.getSousStockId());
            return repository.save(p);
        }).orElseThrow(() -> new RuntimeException("Programme non trouvé"));
    }

    public List<ProgrammeSemaine> getProgrammesBySousStockId(Long sousStockId) {
    return repository.findBySousStockId(sousStockId);
}

    public void deleteProgramme(Long id) {
        repository.deleteById(id);
    }
}
