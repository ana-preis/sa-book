package com.sa.youtube.repositories;

import com.sa.youtube.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);
    Optional<User> findById(UUID id);
}