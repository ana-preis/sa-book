package com.sa.youtube.repositories;

import com.sa.youtube.dtos.ReviewOutDTO;
import com.sa.youtube.models.Review;
import com.sa.youtube.models.ReviewKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, ReviewKey> {

    List<Review> findByVideo_Id(String videoId);

    @Query("SELECT r FROM Review r WHERE r.id.userId = :userId AND r.id.videoId = :videoId")
    Review findByUserIdVideoId(@Param("userId") UUID userId, @Param("videoId") String videoId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.id.videoId = :videoId")
    Double getAverageRating(@Param("videoId") String videoId);

    @Query("SELECT COUNT(r.id.userId) FROM Review r WHERE r.id.videoId = :videoId")
    Long getReviewCount(@Param("videoId") String videoId);

    @Query("""            
        SELECT new com.sa.youtube.dtos.ReviewOutDTO(r, u)
        FROM Review r INNER JOIN r.user u
        WHERE r.id.videoId = :videoId
    """)
    List<ReviewOutDTO> getReviewDTOsByVideoId(@Param("videoId") String videoId);

    @Query("""            
        SELECT new com.sa.youtube.dtos.ReviewOutDTO(r, u)
        FROM Review r INNER JOIN r.user u
        WHERE r.id.userId = :userId
    """)
    List<ReviewOutDTO> getReviewDTOsByUserId(@Param("userId") UUID userId);

}
