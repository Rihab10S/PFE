package com.pfe.back.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
    
@Service
public class FileFactVirementService {
        // Répertoire où les fichiers seront stockés
        @Value("${file.upload-dirFactVirement}")
        private String uploadDir;
    
        // Méthode pour sauvegarder un fichier
        public String saveFile(MultipartFile file) throws IOException {
            // Créer un répertoire si nécessaire
            Path dirPath = Paths.get(uploadDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
    
            // Générer un nom de fichier unique pour éviter les collisions
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = dirPath.resolve(fileName);
    
            // Sauvegarder le fichier dans le répertoire
            Files.write(filePath, file.getBytes());
    
            // Retourner le chemin du fichier enregistré
            return filePath.toString();
        }
    
        // Méthode pour supprimer un fichier
        public void deleteFile(String filePath) throws IOException {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }
    }
    