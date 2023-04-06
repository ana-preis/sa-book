package com.sa.youtube.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    private String name;
    private String phone;
    @Email
    private String email;
    private String password;
    @OneToMany
    private List<Video> toWatchList = new ArrayList<>();
    @OneToMany
    private List<Video> finishedList = new ArrayList<>();
    @Embedded
    private File profilePicture;
}
