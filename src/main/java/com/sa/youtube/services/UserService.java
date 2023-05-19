package com.sa.youtube.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sa.youtube.dtos.UserOutDTO;
import com.sa.youtube.dtos.UserPasswordUpdateDTO;
import com.sa.youtube.dtos.UserInDTO;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public UserOutDTO createUser(UserInDTO dto) {
        User user = new User(dto);
        user.setPassword(encoder.encode(dto.password()));
        return new UserOutDTO(repository.save(user));
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
    public UserOutDTO updateUser(UserInDTO dto, UUID id) {
        User user = repository.findById(id).orElseThrow();
        if (dto.username() != null) {
            user.setName(dto.username());
        }
        if (dto.password() != null) {
            user.setPassword(encoder.encode(dto.password()));
        }
        return new UserOutDTO(repository.save(user));
    }

    public Boolean updateUserPassword(UserPasswordUpdateDTO dto, UUID userId) {
        return true;
    }

    @Transactional
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
