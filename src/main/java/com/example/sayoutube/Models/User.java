package com.example.sayoutube.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Video> toWatchList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Video> finishedList;
}
