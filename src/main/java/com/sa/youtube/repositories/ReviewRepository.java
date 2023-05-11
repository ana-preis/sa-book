package com.sa.youtube.repositories;

import com.sa.youtube.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByVideo_Id(String videoId);

    @Query(
        value = "SELECT AVG(r.rating) FROM REVIEW r WHERE r.video_id = ?1",
        nativeQuery = true
    )
    Double getAverageRating(String videoId);
}
