package com.sa.youtube.Repository;

import com.sa.youtube.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    Optional<Video> findByTitle(String title);
    Optional<Video> findById(String idVideo);
}
