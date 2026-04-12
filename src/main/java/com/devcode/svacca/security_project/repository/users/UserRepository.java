package com.devcode.svacca.security_project.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcode.svacca.security_project.model.roles.entity.Role;

public interface UserRepository extends JpaRepository<Role, Long>{
    
}
