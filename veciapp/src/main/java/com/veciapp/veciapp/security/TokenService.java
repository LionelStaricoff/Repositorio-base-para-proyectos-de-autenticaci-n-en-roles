package com.veciapp.veciapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmConstraints;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;



    public String generateToken(UserDetailsImpl usuario) {
        // Convertir la clave secreta a un arreglo de bytes
        byte[] secretBytes = apiSecret.getBytes(StandardCharsets.UTF_8);

        // Crear la clave secreta usando la clave en bytes
        SecretKey key = Keys.hmacShaKeyFor(secretBytes);

        // Generar el token
        return Jwts.builder()
                .subject(usuario.getUsername())
                .signWith(key) // Usar la clave secreta para firmar
                .compact();
    }


}
