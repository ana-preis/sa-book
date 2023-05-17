package com.sa.youtube.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.UserOutDTO;
import com.sa.youtube.dtos.UserInDTO;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Transactional
    public UserOutDTO createUser(UserInDTO userForm) {
        User saved = repository.save(new User(userForm));
        return new UserOutDTO(saved);
    }

    public UserOutDTO getUserById(UUID id) {
        User user = repository.findById(id).orElseThrow();
        return new UserOutDTO(user);
    }

    public Page<UserOutDTO> getUsers(Pageable page) {
        Page<UserOutDTO> users = repository.findAll(page).map(UserOutDTO::new);
        return users;
    }

    @Transactional
    public UserOutDTO updateUser(UserInDTO userForm, UUID id) {
        User user = repository.findById(id).orElseThrow();
        if (userForm.email() != null) {
            user.setEmail(userForm.email());
        }
        if (userForm.username() != null) {
            user.setUsername(userForm.username());
        }
        if (userForm.password() != null) {
            user.setPassword(userForm.password());
        }
        return new UserOutDTO(repository.save(user));
    }

    @Transactional
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
