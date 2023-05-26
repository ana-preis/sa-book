package com.sa.youtube.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.sa.youtube.models.TokenUpdate;
import com.sa.youtube.models.User;

public interface TokenUpdateRepository extends JpaRepository<TokenUpdate, UUID> {
    Optional<TokenUpdate> findByAccessToken(String accessToken);
    Optional<TokenUpdate> findByRefreshToken(String refreshToken);
    @Modifying
    void deleteByUser(User user);
}
