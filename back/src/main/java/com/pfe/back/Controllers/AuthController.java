package com.pfe.back.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.Services.AuthService;
import com.pfe.back.entities.User;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            
            String token = authService.register(user.getUsername(), user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur créé avec succès. Token : " + token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestHeader("Authorization") String token) {
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token manquant ou mal formé.");
            }

            token = token.substring(7); // Extraire le token sans "Bearer "

            String username = authService.getUsernameFromToken(token); // Récupérer le nom d'utilisateur à partir du token

            if (username == null || !authService.isValidToken(token, username)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide ou expiré.");
            }

            return ResponseEntity.status(HttpStatus.OK).body("Connexion réussie. Bienvenue, " + username);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide ou expiré.");
        }
    }

}
