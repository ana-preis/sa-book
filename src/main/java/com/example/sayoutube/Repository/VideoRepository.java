package com.example.sayoutube.Repository;

import com.example.sayoutube.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    Optional<Video> findByTitle(String title);
    Optional<Video> findById(String idVideo);
}
