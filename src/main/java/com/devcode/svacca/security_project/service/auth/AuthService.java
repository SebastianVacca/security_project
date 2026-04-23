package com.devcode.svacca.security_project.service.auth;

import com.devcode.svacca.security_project.model.auth.dto.LoginRequestDto;
import com.devcode.svacca.security_project.model.auth.dto.LoginResponseDto;
import com.devcode.svacca.security_project.model.auth.dto.RegisterUserDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);

    void register(RegisterUserDto request);
}
