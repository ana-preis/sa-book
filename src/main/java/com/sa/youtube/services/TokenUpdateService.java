package com.sa.youtube.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sa.youtube.models.TokenUpdate;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.TokenUpdateRepository;
import com.sa.youtube.repositories.UserRepository;


@Service
public class TokenUpdateService {

    @Value("${api.security.token.update.expiration.h}")
    private String EXPIRATION_H;

    @Autowired
    private TokenUpdateRepository tokenUpdateRepository;

    @Autowired
    private UserRepository userRepository;

    public TokenUpdate generateUpdateToken(UUID id, String accessToken) {
        User user = userRepository.findById(id).orElseThrow();
        return tokenUpdateRepository.save(
            new TokenUpdate(
                UUID.randomUUID(),
                accessToken,
                UUID.randomUUID().toString(),
                getExpirationDate(),
                user
            )
        );
    }

    private LocalDateTime getExpirationDate() {
        return LocalDateTime
            .now()
            .plusHours(Integer.parseInt(EXPIRATION_H));
    }

    public Boolean checkTokenUpdate(String refreshToken) {
        return tokenUpdateRepository
            .findByRefreshToken(refreshToken)
            .orElseThrow()
            .getExpirationDate()
            .isAfter(LocalDateTime.now());
    }

}
