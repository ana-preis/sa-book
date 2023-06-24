package com.sa.youtube.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa.youtube.infra.security.Token;
import com.sa.youtube.models.User;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByAccessToken(String accessToken);
    Optional<Token> findByRefreshToken(String refreshToken);
    Optional<Token> findByUser(User user);
    @Modifying
    @Query(value = """
        DELETE FROM token t
        WHERE t.user_id = :id""",
        nativeQuery = true
    )
    void deleteByUserId(@Param("id") UUID id);
    void deleteByUser(User user);
}
