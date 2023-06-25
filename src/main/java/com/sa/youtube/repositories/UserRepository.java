package com.sa.youtube.repositories;

import com.sa.youtube.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByEmail(String email);
    Optional<UserDetails> findByName(String username);
    @Query(value = """
        SELECT BIN_TO_UUID(uc.category_id)
        FROM user_category uc
        WHERE uc.user_id = :id""",
        nativeQuery = true
    )
    ArrayList<UUID> getSubscriptions(@Param("id") UUID id);
}