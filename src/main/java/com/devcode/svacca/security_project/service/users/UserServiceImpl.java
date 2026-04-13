package com.devcode.svacca.security_project.service.users;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devcode.svacca.security_project.mapper.users.UserMapper;
import com.devcode.svacca.security_project.model.users.dto.CreateUser;
import com.devcode.svacca.security_project.model.users.dto.ResponseUser;
import com.devcode.svacca.security_project.model.users.dto.UpdateUser;
import com.devcode.svacca.security_project.repository.roles.RoleRepository;
import com.devcode.svacca.security_project.repository.users.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseUser create(CreateUser request) {
        var user = userMapper.toEntity(request);
        user.setHireDate(LocalDate.now());
        user.setActive(true);

        var roles = request.roles().stream()
            .map(r -> roleRepository.findByName(r).orElse(null))
            .filter(r -> r != null)
            .collect(Collectors.toSet());

        user.setRoles(roles);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public ResponseUser update(UpdateUser request) {
        var actualUser = userRepository.findById(request.username())
                    .orElseThrow();

        var user = userMapper.toEntity(request);
        user.setPassword(actualUser.getPassword());
        user.setHireDate(actualUser.getHireDate());
        user.setActive(actualUser.getActive());

        var roles = request.roles().stream()
            .map(r -> roleRepository.findByName(r).orElse(null))
            .filter(r -> r != null)
            .collect(Collectors.toSet());

        user.setRoles(roles);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public List<ResponseUser> getAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toResponse)
            .toList();
    }

    @Override
    public ResponseUser getByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

    @Override
    public void activate(String username) {
        var user = userRepository.findById(username)
                    .orElseThrow();
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void deactivate(String username) {
        var user = userRepository.findById(username)
                    .orElseThrow();
        user.setActive(false);
        userRepository.save(user);
    }
    
}
