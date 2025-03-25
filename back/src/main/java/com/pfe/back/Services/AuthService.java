package com.pfe.back.Services;




import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.back.Repositories.UserRepository;
import com.pfe.back.Security.JwtUtil;
import com.pfe.back.entities.User;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists!");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return jwtUtil.generateToken(username);
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
