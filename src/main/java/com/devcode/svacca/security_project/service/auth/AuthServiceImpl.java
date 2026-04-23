package com.devcode.svacca.security_project.service.auth;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeOperationsException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devcode.svacca.security_project.mapper.users.UserMapper;
import com.devcode.svacca.security_project.model.auth.dto.LoginRequestDto;
import com.devcode.svacca.security_project.model.auth.dto.LoginResponseDto;
import com.devcode.svacca.security_project.model.auth.dto.RegisterUserDto;
import com.devcode.svacca.security_project.repository.roles.RoleRepository;
import com.devcode.svacca.security_project.repository.users.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        try {
            var auth = new UsernamePasswordAuthenticationToken(request.username(), request.password());
            authenticationManager.authenticate(auth);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales inválidas");
        }

        var user = userRepository.findById(request.username()).get();
        var token = String.format("%s:-:%s", user.getUsername(), user.getEmail(), "CUSTOM");

        return new LoginResponseDto(token, "CUSTOM");
    }

    @Override
    public void register(RegisterUserDto request) {
        var exist = userRepository.existsById(request.username());
        if(exist)
            throw new RuntimeException("El usuario ya está registrado");
        
        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setHireDate(LocalDate.now());
        user.setActive(true);

        var role = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("El rol no existe"));
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }
    
}
