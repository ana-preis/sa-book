package com.sa.youtube.controllers;

import com.sa.youtube.dtos.UserOutDTO;
import com.sa.youtube.dtos.UserPasswordUpdateDTO;
import com.sa.youtube.dtos.UserInDTO;
import com.sa.youtube.dtos.UserNameUpdateDTO;
import com.sa.youtube.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{userID}")
    public ResponseEntity<UserOutDTO> getByID(@PathVariable UUID userID) {
        try {
            UserOutDTO user = service.getUserById(userID);
            return new ResponseEntity<UserOutDTO>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<UserOutDTO>> getAll(Pageable page) {
        Page<UserOutDTO> users = service.getUsers(page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserOutDTO> save(@RequestBody @Valid UserInDTO userForm) {
        UserOutDTO newUser = service.createUser(userForm);
        return new ResponseEntity<UserOutDTO>(newUser, HttpStatus.CREATED);
    }

    @PatchMapping("/{userID}")
    public ResponseEntity<UserOutDTO> update(@RequestBody @Valid UserNameUpdateDTO dto, @PathVariable UUID userID) {
        UserOutDTO userDTO = service.updateUser(dto, userID);
        return new ResponseEntity<UserOutDTO>(userDTO, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid UserPasswordUpdateDTO dto, @PathVariable UUID userId) {
        if (service.updateUserPassword(dto, userId)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<?> delete(@PathVariable UUID userID) {
        service.deleteUser(userID);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
