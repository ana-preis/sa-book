package com.sa.youtube.repositories;

import com.sa.youtube.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
    Optional<User> findById(UUID id);
}