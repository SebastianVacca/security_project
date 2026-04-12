package com.devcode.svacca.security_project.model.users.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateUser(

    @NotEmpty(message = "El nombre de usuario es obligatorio")
    String username,

    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    String password,

    @NotEmpty(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    String email,

    @NotEmpty(message = "Debe asignar mínimo un rol")
    Set<String> roles,

    String name) {

}
