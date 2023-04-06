package com.sa.youtube.repositories;

import com.sa.youtube.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
    Optional<Playlist> findByTitle(String title);
    Optional<Playlist> findById(UUID id);
}