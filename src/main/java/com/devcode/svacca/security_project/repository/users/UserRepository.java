package com.devcode.svacca.security_project.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcode.svacca.security_project.model.users.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
