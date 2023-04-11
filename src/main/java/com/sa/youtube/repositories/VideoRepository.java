package com.sa.youtube.repositories;

import com.sa.youtube.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
    Optional<Video> findByTitle(String title);
    Optional<Video> findById(String id);
    List<Video> findAllByTitleContainingIgnoreCase(String title);
}
