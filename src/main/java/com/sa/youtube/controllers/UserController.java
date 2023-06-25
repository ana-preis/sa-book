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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{userID}")
    public ResponseEntity<UserOutDTO> getByID(@PathVariable UUID userID) {
        UserOutDTO userOutDTO = service.getUserById(userID);
        return new ResponseEntity<UserOutDTO>(userOutDTO, HttpStatus.OK);
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
    public ResponseEntity<?> update(@RequestBody @Valid UserNameUpdateDTO dto, @PathVariable UUID userID) {
        if (service.updateUser(dto, userID)) {
            UserOutDTO userOutDTO = service.getUserById(userID);
            return new ResponseEntity<UserOutDTO>(userOutDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid UserPasswordUpdateDTO dto, @PathVariable UUID userId) {
        if (service.updateUserPassword(dto, userId)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Valid UserInDTO userForm, Authentication authentication) {
        service.deleteUser(userForm, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userID}/categories/{categoryID}")
    public ResponseEntity<UserOutDTO> saveCategoryToUser(@PathVariable UUID userID, @PathVariable UUID categoryID) {
        UserOutDTO dto = service.saveCategoryToUser(userID, categoryID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{userID}/categories/{categoryID}")
    public ResponseEntity<UserOutDTO> deleteCategoryToUser(@PathVariable UUID userID, @PathVariable UUID categoryID) {
        UserOutDTO dto = service.deleteCategoryToUser(userID, categoryID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<UserOutDTO> createAdmin() {
        UserOutDTO dto = service.createAdminUser();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
