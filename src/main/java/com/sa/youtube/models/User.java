package com.sa.youtube.models;

import com.sa.youtube.dtos.UserInDTO;
import com.sa.youtube.infra.security.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviewList = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    private Boolean isEnabled = true;

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


    public User(UserInDTO dto) {
        this.name = dto.username();
        this.email = dto.email();
        this.password = dto.password();
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.ROLE_ADMIN;
    }

    public Boolean addCategory(Category category) {
        return this.subscriptions.add(category);
    }

    public Boolean removeCategory(Category category) {
        return this.subscriptions.remove(category);
    }


    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}