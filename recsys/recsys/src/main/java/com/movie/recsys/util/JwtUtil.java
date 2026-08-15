package com.movie.recsys.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {

        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

        this.expiration = expiration;
    }


    public String generateToken(
            Integer userId,
            String email,
            String role,
            String firstName
    ) {

        Date now = new Date();

        Date expiry =
                new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("email", email)
                .claim("role", role)
                .claim("firstName", firstName)
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretKey)
                .compact();
    }


    public Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public Integer extractUserId(String token) {

        return Integer.valueOf(
                extractClaims(token).getSubject()
        );
    }


    public String extractEmail(String token) {

        return extractClaims(token)
                .get("email", String.class);
    }


    public String extractRole(String token) {

        return extractClaims(token)
                .get("role", String.class);
    }


    public String extractFirstName(String token) {

        return extractClaims(token)
                .get("firstName", String.class);
    }


    public boolean isTokenValid(String token) {

        try {

            Claims claims = extractClaims(token);

            return claims.getExpiration()
                    .after(new Date());

        } catch (Exception e) {

            return false;
        }
    }
}