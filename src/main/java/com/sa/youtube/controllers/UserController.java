package com.sa.youtube.controllers;

import com.sa.youtube.controllers.dtos.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @PutMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO userForm) {
        UserDTO user = new UserDTO(userForm.id(), userForm.name(), userForm.email(), userForm.password(), userForm.profilePicture());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
