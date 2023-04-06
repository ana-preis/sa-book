package com.sa.youtube.repositories;

import com.sa.youtube.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findById(UUID id);
}
