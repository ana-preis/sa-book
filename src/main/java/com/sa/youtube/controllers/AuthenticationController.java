package com.sa.youtube.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sa.youtube.dtos.JWTRequestDTO;
import com.sa.youtube.dtos.JWTResponseDTO;
import com.sa.youtube.dtos.RefreshTokenDTO;
import com.sa.youtube.dtos.UserOutDTO;
import com.sa.youtube.models.User;
import com.sa.youtube.services.TokenService;
import com.sa.youtube.services.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @Autowired
    private UserService userService;

    @Transactional
    @GetMapping("/me")
    public ResponseEntity<UserOutDTO> getMyself(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserOutDTO dto = userService.getUserById(user.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid JWTRequestDTO data) {
        try {
            Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(), data.password())
            );
            System.out.println(auth);
            User user = (User) auth.getPrincipal();
            JWTResponseDTO jwt = service.login(user.getId());
            return new ResponseEntity<JWTResponseDTO>(jwt, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponseDTO> refresh(@RequestBody @Valid RefreshTokenDTO dto) {
        JWTResponseDTO jwt = service.refresh(dto.refreshToken());
        return new ResponseEntity<JWTResponseDTO>(jwt, HttpStatus.OK);
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<?> revoke(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        service.deleteByUserId(user.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
