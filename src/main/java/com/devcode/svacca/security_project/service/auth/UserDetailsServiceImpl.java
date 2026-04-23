package com.devcode.svacca.security_project.service.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcode.svacca.security_project.repository.users.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var roles = user.getRoles().stream()
            .map(r -> new SimpleGrantedAuthority(r.getName())).toList();

        return User.builder()
            .username(username)
            .authorities(roles)
            .password(user.getPassword())
            .build();
    }
    
    
}
