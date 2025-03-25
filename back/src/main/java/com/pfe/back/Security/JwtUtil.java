package com.pfe.back.Security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    

    public String generateToken(String username) {
        return Jwts.builder()
        .setSubject(username)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
    
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
    public boolean validateToken(String token) {
    try {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)  
                .parseClaimsJws(token);   
        return claimsJws.getBody() != null; 
    } catch (JwtException e) {
       
        throw new RuntimeException("Invalid JWT token");
    }
}


}