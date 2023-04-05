package com.sa.youtube.repositories;

import com.sa.youtube.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    Optional<Video> findByTitle(String title);
    Optional<Video> findById(UUID id);
}
