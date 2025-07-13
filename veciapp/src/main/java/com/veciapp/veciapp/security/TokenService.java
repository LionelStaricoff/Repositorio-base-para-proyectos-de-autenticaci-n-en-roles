package com.veciapp.veciapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;



    private SecretKey generateKey(){
        // Convertir la clave secreta a un arreglo de bytes
        byte[] secretBytes = apiSecret.getBytes(StandardCharsets.UTF_8);

        // Crear la clave secreta usando la clave en bytes
        return Keys.hmacShaKeyFor(secretBytes);
    }
    public String generateToken(UserDetailsImpl usuario) {


        // Generar el token
        return Jwts.builder()
                .subject(usuario.getUsername())
                .signWith(generateKey()) // Usar la clave secreta para firmar
                .compact();
    }

    public Instant generatFechaExpiracion(){
       return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);

    }

    public String getSubjetct(String token){
        Jws<Claims> verifier =null;
        try {

             verifier = Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token);

        } catch (JwtException e) {

            throw new RuntimeException(e);
        }
        if(verifier.getPayload().getSubject()==null) throw new RuntimeException("verificacion invalida");
        return verifier.getPayload().getSubject();
    }

}
