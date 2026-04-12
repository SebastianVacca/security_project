package com.devcode.svacca.security_project.model.users.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.context.support.BeanDefinitionDsl.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
    
    @Id
    @Column(length = 50)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(name = "full_name")
    private String name;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @ManyToMany
    @JoinTable(name = "user_roles",
     joinColumns = @JoinColumn(columnDefinition = "user_id"),
     inverseJoinColumns = @JoinColumn(columnDefinition = "role_id")
    )
    private Set<Role> roles;

    private Boolean active;
}
