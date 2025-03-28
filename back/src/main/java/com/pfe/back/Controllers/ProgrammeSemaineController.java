package com.pfe.back.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.Services.ProgrammeSemaineService;
import com.pfe.back.entities.ProgrammeSemaine;

@RestController
@RequestMapping("/api/programmes")

public class ProgrammeSemaineController {

    @Autowired
    private ProgrammeSemaineService service;

    @PostMapping
    public ResponseEntity<ProgrammeSemaine> createProgramme(@RequestBody ProgrammeSemaine programme) {
        return ResponseEntity.ok(service.addProgramme(programme));
    }

    @GetMapping
    public ResponseEntity<List<ProgrammeSemaine>> getAllProgrammes() {
        return ResponseEntity.ok(service.getAllProgrammes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProgrammeSemaine> getProgrammeById(@PathVariable Long id) {
        ProgrammeSemaine programme = service.getProgrammeById(id);
        if (programme != null) {
            return ResponseEntity.ok(programme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProgrammeSemaine> updateProgramme(
            @PathVariable Long id,
            @RequestBody ProgrammeSemaine programme) {
        return ResponseEntity.ok(service.updateProgramme(id, programme));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramme(@PathVariable Long id) {
        service.deleteProgramme(id);
        return ResponseEntity.noContent().build();
    }
}
