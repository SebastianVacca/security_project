package com.devcode.svacca.security_project.model.users.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public record ResponseUser(

    String username,

    String email,

    String name,

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty(value = "hire_date")
    LocalDate hireDate,

    Boolean active,
    
    Set<String> roles) {
    
}
