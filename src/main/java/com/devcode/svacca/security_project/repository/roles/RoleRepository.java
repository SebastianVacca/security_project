package com.devcode.svacca.security_project.repository.roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcode.svacca.security_project.model.roles.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String name);
    
}
