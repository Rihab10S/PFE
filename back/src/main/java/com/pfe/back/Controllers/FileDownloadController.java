package com.pfe.back.Controllers;


import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/download")
public class FileDownloadController {

    private static final String UPLOAD_DIR = "Téléchargements/";

    @GetMapping("/bon-commande/{filename}")
    public ResponseEntity<Resource> downloadBonCommande(@PathVariable String filename) {
        Path filePath = Paths.get(UPLOAD_DIR + filename);
        Resource resource = new FileSystemResource(filePath);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + filename)
                .body(resource);
    }

    @GetMapping("/facture/{filename}")
    public ResponseEntity<Resource> downloadFacture(@PathVariable String filename) {
        Path filePath = Paths.get(UPLOAD_DIR + filename);
        Resource resource = new FileSystemResource(filePath);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + filename)
                .body(resource);
    }

    @GetMapping("/virement/{filename}")
    public ResponseEntity<Resource> downloadVirement(@PathVariable String filename) {
        Path filePath = Paths.get(UPLOAD_DIR + filename);
        Resource resource = new FileSystemResource(filePath);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + filename)
                .body(resource);
    }

    @GetMapping("/bon-reception/{filename}")
    public ResponseEntity<Resource> downloadBonReception(@PathVariable String filename) {
        Path filePath = Paths.get(UPLOAD_DIR + filename);
        Resource resource = new FileSystemResource(filePath);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + filename)
                .body(resource);
    }
}
