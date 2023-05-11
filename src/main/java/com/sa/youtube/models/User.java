package com.sa.youtube.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.sa.youtube.dtos.UserForm;

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
@Table(name="user_")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Name can't be null")
    private String username;

    @Email
    private String email;

    @Column(name = "password_")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToMany(mappedBy = "userList")
    private List<Category> subscriptions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_video_to_watch",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<Video> toWatchList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_video_finished",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<Video> finishedList = new ArrayList<>();


    public User(UserForm dto) {
        this.username = dto.username();
        this.email = dto.email();
        this.password = dto.password();
    }

}