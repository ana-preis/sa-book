package com.sa.youtube.controllers;

import com.sa.youtube.dtos.UserDTO;
import com.sa.youtube.dtos.UserForm;
import com.sa.youtube.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getByID(@PathVariable UUID userID) {
        try {
            UserDTO user = service.getUserById(userID);
            return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAll(Pageable page) {
        Page<UserDTO> users = service.getUsers(page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserForm userForm) {
        UserDTO newUser = service.createUser(userForm);
        return new ResponseEntity<UserDTO>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserForm userForm, @PathVariable UUID userID) {
        try {
            UserDTO userDTO = service.updateUser(userForm, userID);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<?> delete(@PathVariable UUID userID) {
        service.deleteUser(userID);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
