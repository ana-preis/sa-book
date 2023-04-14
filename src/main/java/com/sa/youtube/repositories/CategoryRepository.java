package com.sa.youtube.repositories;

import com.sa.youtube.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(String name);
    Optional<Category> findById(UUID id);
    List<Category> findByNameContaining(String text);
}