package com.pfe.back.Services;




import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.RoleRepository;
import com.pfe.back.Repositories.UserRepository;
import com.pfe.back.Security.JwtUtil;
import com.pfe.back.entities.Erole;
import com.pfe.back.entities.Role;
import com.pfe.back.entities.User;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, JwtUtil jwtUtil,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
         this.passwordEncoder = passwordEncoder;
    }

    public String register(String username, String password, Set<String> strRoles) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        User user = new User();
        user.setUsername(username);
        // **Encoder le mot de passe avant de le sauvegarder**
        user.setPassword(passwordEncoder.encode(password));
        

        Set<Role> roles = new HashSet<>();
        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(Erole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Erreur : rôle non trouvé."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        roles.add(roleRepository.findByName(Erole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle admin non trouvé.")));
                        break;
                    case "magasinier":
                        roles.add(roleRepository.findByName(Erole.ROLE_MAGASINIER)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle magasinier non trouvé.")));
                        break;
                    case "technicien":
                        roles.add(roleRepository.findByName(Erole.ROLE_TECHNICIEN)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle technicien non trouvé.")));
                        break;
                    case "ingenieur":
                        roles.add(roleRepository.findByName(Erole.ROLE_INGENIEUR)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle ingénieur non trouvé.")));
                        break;
                    case "service comptable":
                    case "service_comptable":
                    case "servicecomptable":
                        roles.add(roleRepository.findByName(Erole.ROLE_SERVICECOMPTABLE)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle service comptable non trouvé.")));
                        break;
                    case "fournisseur":
                        roles.add(roleRepository.findByName(Erole.ROLE_FOURNISSEUR)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle fournisseur non trouvé.")));
                        break;
                    case "responsable d'achat":
                    case "responsable_achat":
                    case "responsableachat":
                        roles.add(roleRepository.findByName(Erole.ROLE_RESPONSABLE_ACHAT)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle responsable d'achat non trouvé.")));
                        break;
                    default:
                        roles.add(roleRepository.findByName(Erole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Erreur : rôle utilisateur non trouvé.")));
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return jwtUtil.generateToken(username);
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }


    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid credentials");
    }
    public String getUsernameFromToken(String token) {
        if (jwtUtil.validateToken(token)) {
            return jwtUtil.extractUsername(token);
        }
        throw new RuntimeException("Token invalide ou expiré");
    }
    // Méthode pour valider un token JWT
    public boolean isValidToken(String token, String username) {
        return jwtUtil.validateToken(token) && username.equals(jwtUtil.extractUsername(token));
    }

    
}
