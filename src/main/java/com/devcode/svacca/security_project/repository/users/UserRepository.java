package com.devcode.svacca.security_project.repository.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcode.svacca.security_project.model.users.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

    Optional<User> findByUsernameAndPassword(String username, String password);
    
}
