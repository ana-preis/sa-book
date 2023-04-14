package com.sa.youtube.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.UserDTO;
import com.sa.youtube.dtos.UserForm;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository repository;

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
}
