package com.devcode.svacca.security_project.model.users.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UpdateUser(
    @NotEmpty(message = "El nombre de usuario es obligatorio")
    String username,

    @NotEmpty(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    String email,

    @NotEmpty(message = "Debe asignar mínimo un rol")
    Set<String> roles,

    String name) {
    
}
