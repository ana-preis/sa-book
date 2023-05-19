package com.sa.youtube.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sa.youtube.models.User;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String SECRET;

    @Value("{api.security.token.issuer}")
    private String ISSUER;

    @Value("{api.security.token.expiration_min}")
    private Long EXPIRATION_MIN;

    public String generateToken(User user) {
        try {
            Algorithm alg = Algorithm.HMAC256(SECRET);
            return JWT
                .create()
                .withIssuer(ISSUER)
                .withSubject(user.getUsername())
                .withExpiresAt(expirationDate())
                .sign(alg);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Token generation error!", e);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime
            .now()
            .plusMinutes(EXPIRATION_MIN)
            .toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm alg = Algorithm.HMAC256(SECRET);
            return JWT
                .require(alg)
                .withIssuer(ISSUER)
                .build()
                .verify(tokenJWT)
                .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token is expired or not valid!", e);
        }
    }

}
