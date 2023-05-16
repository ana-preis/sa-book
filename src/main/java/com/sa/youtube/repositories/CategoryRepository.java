package com.sa.youtube.repositories;

import com.sa.youtube.models.Category;
import com.sa.youtube.models.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(String name);
    List<Category> findByNameContaining(String text);

    @Query(value = """
        SELECT
            SUM(v.view_count)
        FROM
            VIDEO v
        LEFT JOIN
            VIDEO_CATEGORY c
        ON
            v.id = c.video_id
        WHERE
            c.category_id = :id""",
        nativeQuery = true
    )
    Long getViewCount(@Param("id") UUID id);

    @Query("""
        SELECT v
        FROM
            Video v
        LEFT JOIN
            Category c
        ON
            v.id = c.video_id
        WHERE
            c.category_id = :id"""
    )
    List<Video> getVideoDTOsByCategoryId(@Param("id") UUID id);
}