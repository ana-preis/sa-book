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
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> userList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Video> videoList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<ForumMessage> forumMessages = new ArrayList<>();
    private Long viewCount;
}
