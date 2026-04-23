package com.devcode.svacca.security_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devcode.svacca.security_project.model.auth.dto.LoginRequestDto;
import com.devcode.svacca.security_project.model.auth.dto.LoginResponseDto;
import com.devcode.svacca.security_project.model.auth.dto.RegisterUserDto;
import com.devcode.svacca.security_project.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto login) {
        return authService.login(login);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterUserDto request) {
        authService.register(request);
    }
    
}
