package com.sa.youtube.repositories;

import com.sa.youtube.models.ForumMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ForumMessageRepository extends JpaRepository<ForumMessage, Integer> {
    Optional<ForumMessage> findByCategory_Id(UUID categoryId);
    Optional<ForumMessage> findById(UUID id);
}