package com.instapro.com.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

 
    private static final String SECRET_KEY =
            "VGhpc19pc19hX3ZlcnlfbG9uZ19zZWNyZXRfa2V5X2Zvcl9qd3RfdG9rZW5fMTIzNDU2";

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 day

    
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // =======================
    // 🎫 GENERATE TOKEN
    // =======================
    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =======================
    // ✅ VALIDATE TOKEN
    // =======================
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // =======================
    // 👤 EXTRACT USERNAME
    // =======================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // =======================
    // 🛡️ EXTRACT ROLE
    // =======================
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // =======================
    // 📦 INTERNAL CLAIMS
    // =======================
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
