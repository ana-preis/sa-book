package com.sa.youtube.repositories;

import com.sa.youtube.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VideoRepository extends JpaRepository<Video, String> {
    Optional<Video> findByTitle(String title);
    Optional<Video> findById(String id);
    List<Video> findAllByTitleContainingIgnoreCase(String title);
}
