package com.sa.youtube.repositories;

import com.sa.youtube.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, String> {
    Optional<Playlist> findByTitle(String title);
    Optional<Playlist> findById(String id);
}