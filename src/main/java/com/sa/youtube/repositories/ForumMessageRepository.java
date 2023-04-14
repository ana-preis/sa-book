package com.sa.youtube.repositories;

import com.sa.youtube.models.ForumMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ForumMessageRepository extends JpaRepository<ForumMessage, UUID> {
    //Optional<ForumMessage> findByCategory_Id(UUID categoryId);
    //Optional<ForumMessage> findById(UUID id);
}