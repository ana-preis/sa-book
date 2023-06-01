package com.sa.youtube.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sa.youtube.dtos.JWTRequestDTO;
import com.sa.youtube.dtos.JWTResponseDTO;
import com.sa.youtube.infra.security.Token;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.TokenRepository;
import com.sa.youtube.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String SECRET;

    @Value("${api.security.token.issuer}")
    private String ISSUER;

    @Value("${api.security.token.expiration.access.min}")
    private String EXPIRATION_ACCESS;

    @Value("${api.security.token.expiration.refresh.min}")
    private String EXPIRATION_REFRESH;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public String generateAccessToken(User user) {
        try {
            Algorithm alg = Algorithm.HMAC256(SECRET);
            return JWT
                .create()
                .withIssuer(ISSUER)
                .withSubject(user.getUsername())
                .withExpiresAt(getExpirationInstant(EXPIRATION_ACCESS))
                .sign(alg);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Token generation error!", e);
        }
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

    private Instant getExpirationInstant(String expiration) {
        return LocalDateTime
            .now()
            .plusMinutes(Integer.parseInt(expiration))
            .toInstant(ZoneOffset.of("-03:00"));
    }

    public Boolean isNotExpired(String refreshToken) {
        return tokenRepository
            .findByRefreshToken(refreshToken)
            .orElseThrow()
            .getExpiresAt()
            .isAfter(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")));
    }

    public Boolean isNotExpired(Token token) {
        return token.getExpiresAt()
            .isAfter(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")));
    }

    public Token createToken(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return tokenRepository.save(
            new Token(
                UUID.randomUUID(),
                getExpirationInstant(EXPIRATION_REFRESH),
                user,
                generateAccessToken(user),
                UUID.randomUUID().toString()
            )
        );
    }

    @Transactional
    public Token createToken(User user) {
        return tokenRepository.save(
            new Token(
                UUID.randomUUID(),
                getExpirationInstant(EXPIRATION_REFRESH),
                user,
                generateAccessToken(user),
                UUID.randomUUID().toString()
            )
        );
    }

    public Token getByRefreshToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken).orElseThrow();
    }

    @Transactional
    public void deleteByUserId(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        tokenRepository.deleteByUser(user);
    }

    @Transactional
    public void deleteByUser(User user) {
        tokenRepository.deleteByUser(user);
    }

    public JWTResponseDTO login(User user) {
        return new JWTResponseDTO(createToken(user));
    }

    public JWTResponseDTO refresh(String refreshToken) {
        Token token = getByRefreshToken(refreshToken);
        User user = token.getUser();
        deleteByUser(user);
        if (isNotExpired(token)) {
            return new JWTResponseDTO(createToken(user));
        }
        throw new RuntimeException("Refresh token expired, please make a new signin request.");
    }

}
