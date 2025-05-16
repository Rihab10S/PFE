package com.pfe.back.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.back.Repositories.RoleRepository;
import com.pfe.back.Repositories.UserRepository;
import com.pfe.back.Requests.AuthenticationRequest;
import com.pfe.back.Requests.UserRegistrationRequest;
import com.pfe.back.Responses.MessageResponse;
import com.pfe.back.Security.JwtUtil;
import com.pfe.back.Services.AuthService;
import com.pfe.back.entities.User;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
     private final AuthenticationManager authenticationManager;
     private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService authService,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder,
                           JwtUtil jwtUtil) {
                            
        this.authService = authService;
         this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

   @PostMapping("/signup")
public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest signUpRequest) {
    try {
        String token = authService.register(
            signUpRequest.getUsername(),
            signUpRequest.getPassword(),
            signUpRequest.getRoles()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("{\"token\":\"" + token + "\"}");
    } catch (AuthService.UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest()
                .body(new MessageResponse("Erreur: " + ex.getMessage()));
    } catch (RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("Erreur serveur: " + ex.getMessage()));
    }
}


   @PostMapping("/signin")
public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

        SecurityContextHolder.getContext().setAuthentication((org.springframework.security.core.Authentication) authentication);

        // Génère le token JWT basé sur le username
        String jwt = jwtUtil.generateToken(authRequest.getUsername());


        // Récupère l'utilisateur pour ses rôles
        User user = userRepository.findByUsername(authRequest.getUsername())
                                  .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Récupère la liste des rôles sous forme de String
        List<String> roles = user.getRoles().stream()
                                .map(role -> role.getName().name()) // par ex. "ROLE_ADMIN"
                                .collect(Collectors.toList());

        // Prépare la réponse JSON avec token, username et roles
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("username", user.getUsername());
        response.put("roles", roles);

        return ResponseEntity.ok(response);

    } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect.");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur.");
    }
}
}