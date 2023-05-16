package com.sa.youtube.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.sa.youtube.dtos.UserForm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
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
    private Set<Review> reviewList = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "user_category",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> subscriptions = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "user_video_to_watch",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Video> toWatchList = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "user_video_finished",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Video> finishedList = new HashSet<>();


    public User(UserForm dto) {
        this.username = dto.username();
        this.email = dto.email();
        this.password = dto.password();
    }

}