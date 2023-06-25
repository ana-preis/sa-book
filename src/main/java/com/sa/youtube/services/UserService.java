package com.sa.youtube.services;

import java.util.*;

import com.sa.youtube.dtos.*;
import com.sa.youtube.models.Category;
import com.sa.youtube.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sa.youtube.models.User;
import com.sa.youtube.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional
    public UserOutDTO createUser(UserInDTO dto) {
        Optional<UserDetails> userOpt = repository.findByEmail(dto.email());
        if(userOpt.isPresent()) throw new IllegalArgumentException("Email já cadastrado");
        Optional<UserDetails> user2Opt = repository.findByName(dto.username());
        if(user2Opt.isPresent()) throw new IllegalArgumentException("Username já cadastrado");
        User user = new User(dto);
        user.setPassword(encoder.encode(dto.password()));
        return getUserOutDTO(repository.save(user));
    }

    public UserOutDTO getUserById(UUID id) {
        User user = repository.findById(id).orElseThrow();
        return getUserOutDTO(user);
    }

    @Transactional
    public UserOutDTO getUserOutDTO(User user) {
        var subscriptions = user.getSubscriptions().stream().map(Category::getId).toList();
        var reviews = user.getReviewList().stream().map((r) -> new ReviewOutDTO(r, user)).toList();
        return new UserOutDTO(user, subscriptions, reviews);
    }

    public Page<UserOutDTO> getUsers(Pageable page) {
        Page<UserOutDTO> users = repository.findAll(page).map(UserOutDTO::new);
        return users;
    }

    @Transactional
    public Boolean updateUser(UserNameUpdateDTO dto, UUID id) {
        User user = repository.findById(id).orElseThrow();
        if (encoder.matches(dto.password(), user.getPassword())) {
            user.setName(dto.username());
            repository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean updateUserPassword(UserPasswordUpdateDTO dto, UUID id) {
        User user = repository.findById(id).orElseThrow();
        if (encoder.matches(dto.oldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(dto.newPassword()));
            repository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteUser(UserInDTO dto, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        if (encoder.matches(dto.password(), user.getPassword()) &&
            dto.email().equals(user.getEmail()) &&
            dto.username().equals(user.getName())
            ) {
                user.setName("Deleted user");
                user.setIsEnabled(false);
                repository.save(user);
            }
    }

    @Transactional
    public UserOutDTO saveCategoryToUser(UUID id, UUID categoryID) {
        User user = repository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(categoryID).orElseThrow();

        user.addCategory(category);
        return getUserOutDTO(repository.save(user));
    }

    @Transactional
    public UserOutDTO deleteCategoryToUser(UUID id, UUID categoryID) {
        User user = repository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(categoryID).orElseThrow();

        user.removeCategory(category);
        return getUserOutDTO(repository.save(user));
    }

    @Transactional
    public UserOutDTO createAdminUser() {
        User admin = new User("admin", "admin@admin.com", encoder.encode("12345678"));
        User saved = repository.save(admin);
        return new UserOutDTO(saved);
    }

}
