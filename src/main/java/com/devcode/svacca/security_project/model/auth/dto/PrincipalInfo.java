package com.devcode.svacca.security_project.model.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrincipalInfo {
    private String email;
    private String username;
    private String name;
    private String hireDate;
}
