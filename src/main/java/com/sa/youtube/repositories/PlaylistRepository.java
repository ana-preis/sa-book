package com.sa.youtube.repositories;

import com.sa.youtube.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    Optional<Playlist> findByTitle(String title);
    Optional<Playlist> findById(UUID id);
}