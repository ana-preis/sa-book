package com.sa.youtube.controllers;

import com.sa.youtube.dtos.UserDTO;
import com.sa.youtube.dtos.UserForm;
import com.sa.youtube.models.User;
import com.sa.youtube.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserForm userForm) {
        User newUser = new User();
        newUser.setName(userForm.name());
        newUser.setEmail(userForm.email());
        newUser.setPassword(userForm.password());
        User saved =  repository.save(newUser);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserForm userForm, @PathVariable UUID userID) {
        Optional<User> optUser = repository.findById(userID);
        User newUser = optUser.get();
        newUser.setName(userForm.name());
        newUser.setEmail(userForm.email());
        newUser.setPassword(userForm.password());
        User saved = repository.save(newUser);
        UserDTO userDTO = new UserDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getProfilePicture());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getByID(@PathVariable UUID userID) {
        Optional<User> optUser = repository.findById(userID);
        User user = optUser.get();
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getProfilePicture());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<User>> getAll(Pageable page) {
        Page<User> users = repository.findAll(page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
