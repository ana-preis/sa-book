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
    private String name;
    @Email
    private String email;
    private String password;
    @ManyToMany
    private List<Video> toWatchList = new ArrayList<>();
    @ManyToMany
    private List<Video> finishedList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Message> messageList = new ArrayList<>();
    @Embedded
    private File profilePicture;

    public User(UserForm dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        //this.profilePicture = dto.profilePicture();
    }


}
