package com.sa.youtube.repositories;

import com.sa.youtube.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


public interface VideoRepository extends JpaRepository<Video, String> {
    Optional<Video> findByTitle(String title);
    List<Video> findAllByTitleContainingIgnoreCase(String title);

    @Query(value = """
        SELECT *
        FROM video v INNER JOIN video_category vc ON
        v.id = vc.video_id
        WHERE vc.category_id = :id""",
        nativeQuery = true
    )
    Optional<Set<Video>> getVideoListByCategoryId(@Param("id") UUID id);

    @Query(value = """
        SELECT SUM(v.view_count)
        FROM video v INNER JOIN video_category vc ON
        v.id = vc.video_id
        WHERE vc.category_id = :id""",
        nativeQuery = true
    )
    Long getViewCountByCategoryId(@Param("id") UUID id);

    @Query(value = """
        SELECT *
        FROM video v INNER JOIN video_category vc ON
        v.id = vc.video_id
        WHERE vc.category_id = :id
        AND (
            v.title LIKE %:text%
            OR
            v.description LIKE %:text%
        )""",
            nativeQuery = true
    )
    List<Video> getAllByNameInCategory(@Param("id") UUID id, @Param("text") String text);
}
