package com.sa.youtube.repositories;

import com.sa.youtube.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByVideoID(UUID videoId);
    Optional<Review> findById(UUID id);
}
