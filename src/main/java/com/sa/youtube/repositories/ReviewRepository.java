package com.sa.youtube.repositories;

import com.sa.youtube.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByVideo_Id(String videoId);
}
