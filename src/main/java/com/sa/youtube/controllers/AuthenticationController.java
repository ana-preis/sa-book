package com.sa.youtube.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sa.youtube.dtos.JWTRequestDTO;
import com.sa.youtube.dtos.JWTResponseDTO;
import com.sa.youtube.dtos.RefreshTokenDTO;
import com.sa.youtube.models.User;
import com.sa.youtube.services.TokenService;

import jakarta.validation.Valid;


@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService service;

    @PostMapping("/login")
    public ResponseEntity<JWTResponseDTO> login(@RequestBody @Valid JWTRequestDTO data) {
        Authentication auth = manager.authenticate(
            new UsernamePasswordAuthenticationToken(data.email(), data.password())
        );
        System.out.println(auth.toString());
        return new ResponseEntity<JWTResponseDTO>(service.login((User) auth.getPrincipal()), HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTResponseDTO> refresh(@RequestBody @Valid RefreshTokenDTO dto) {
        return new ResponseEntity<JWTResponseDTO>(service.refresh(dto.refreshToken()), HttpStatus.OK);
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<?> revoke(Authentication authentication) {
        service.deleteByUser((User) authentication.getPrincipal());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
