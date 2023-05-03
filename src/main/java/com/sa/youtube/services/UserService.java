package com.sa.youtube.services;

import java.util.Optional;
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

    public UserDTO getUserById(UUID id) throws Exception {
        Optional<User> userOpt = repository.findById(id);
        if (userOpt.isPresent()) {
            return new UserDTO(userOpt.get());
        }
        throw new Exception();
    }

    public Page<UserDTO> getUsers(Pageable page) {
        Page<UserDTO> users = repository.findAll(page).map(UserDTO::new);
        return users;
    }

    @Transactional
    public UserDTO updateUser(UserForm userForm, UUID id) throws Exception {
        Optional<User> userOpt = repository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
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
        throw new Exception();
    }

    @Transactional
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
