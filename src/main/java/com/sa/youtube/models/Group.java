package com.sa.youtube.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<User> userList;
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Video> videoList;
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<ForumMessage> forumMessages;
}
