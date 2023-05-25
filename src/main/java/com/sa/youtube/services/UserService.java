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
import com.sa.youtube.dtos.UserNameUpdateDTO;
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
    public UserOutDTO updateUser(UserNameUpdateDTO dto, UUID id) {
        User user = repository.findById(id).orElseThrow();
        user.setName(dto.username());
        return new UserOutDTO(repository.save(user));
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
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }
}
