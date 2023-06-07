package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Column(length = 2000)
    private String description;

    private Long viewCount;

    @ManyToMany(mappedBy = "subscriptions")
    private List<User> userList = new ArrayList<>();

    @ManyToMany(mappedBy = "categoryList")
    private Set<Video> videoList = new HashSet<>();

}