package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
        name = "category_user",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "category_video",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<Video> videoList = new ArrayList<>();


    public Long getViewCount() {
        return 0L;
    }

}