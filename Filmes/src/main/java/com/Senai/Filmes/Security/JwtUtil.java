package com.Senai.Filmes.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey getChave() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    //
    public String gerarToken(UserDetails userDetails) {

        Date agora = new Date();
        Date exp = new Date(agora.getTime() + expiration);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(agora)
                .expiration(exp)
                .signWith(getChave())
                .compact();
    }

    //
    public String extrairEmail(String token) {
        return Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //
    public boolean validarToken(String token, UserDetails userDetails) {
        try {
            String email = extrairEmail(token);
            return email.equals(userDetails.getUsername()) && !isTokenExpirado(token);
        } catch (Exception e) {
            return false;
        }
    }

    //
    private boolean isTokenExpirado(String token) {
        try {
            Date expirationDate = Jwts.parser()
                    .verifyWith(getChave())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration();

            return expirationDate.before(new Date());

        } catch (Exception e) {
            return true;
        }
    }
}