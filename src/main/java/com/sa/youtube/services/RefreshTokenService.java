package com.sa.youtube.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sa.youtube.models.Token;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.TokenRepository;
import com.sa.youtube.repositories.UserRepository;


@Service
public class RefreshTokenService {

    @Value("${api.security.token.update.expiration.h}")
    private String EXPIRATION_H;

    @Autowired
    private TokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Token generateRefreshToken(UUID id, String accessToken) {
        User user = userRepository.findById(id).orElseThrow();
        return refreshTokenRepository.save(
            new Token(
                UUID.randomUUID(),
                getExpirationDate(),
                user,
                accessToken,
                UUID.randomUUID().toString()
            )
        );
    }

    public Token getByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow();
    }

    private LocalDateTime getExpirationDate() {
        return LocalDateTime
            .now()
            .plusHours(Integer.parseInt(EXPIRATION_H));
    }

    public Boolean checkRefreshTokenExpiration(String refreshToken) {
        return refreshTokenRepository
            .findByRefreshToken(refreshToken)
            .orElseThrow()
            .getExpirationDate()
            .isAfter(LocalDateTime.now());
    }

    public void deleteByUserId(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        refreshTokenRepository.deleteByUser(user);
    }

}
