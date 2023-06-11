package com.sa.youtube.services;

import java.util.*;

import com.sa.youtube.models.Category;
import com.sa.youtube.repositories.CategoryRepository;
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
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    @Transactional
    public UserOutDTO saveCategoryToUser(UUID id, UUID categoryID) {
        User user = repository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(categoryID).orElseThrow();
        if (user.getSubscriptions().contains(category)) throw new RuntimeException();
        Set<Category> subscriptions = new HashSet<>();
        subscriptions.add(category);
        user.setSubscriptions(subscriptions);
        User saved = repository.save(user);
        return new UserOutDTO(saved);
    }

    @Transactional
    public UserOutDTO deleteCategoryToUser(UUID id, UUID categoryID) {
        User user = repository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(categoryID).orElseThrow();

        Set<Category> newCategories = user.getSubscriptions();
        newCategories.remove(category);
        user.setSubscriptions(newCategories);
        User saved = repository.save(user);

        List<User> newUserList = category.getUserList();
        newUserList.remove(user);
        category.setUserList(newUserList);
        categoryRepository.save(category);

        return new UserOutDTO(saved);
    }

}
