package com.sa.youtube.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.UserDTO;
import com.sa.youtube.dtos.UserForm;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Transactional
    public UserDTO createUser(UserForm userForm) {
        User saved = repository.save(new User(userForm));
        return new UserDTO(saved);
    }

    public UserDTO getUserById(UUID id) {
        User user = repository.findById(id).orElseThrow();
        return new UserDTO(user);
    }

    public Page<UserDTO> getUsers(Pageable page) {
        Page<UserDTO> users = repository.findAll(page).map(UserDTO::new);
        return users;
    }

    @Transactional
    public UserDTO updateUser(UserForm userForm, UUID id) {
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
        return new UserDTO(repository.save(user));
    }

    @Transactional
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
