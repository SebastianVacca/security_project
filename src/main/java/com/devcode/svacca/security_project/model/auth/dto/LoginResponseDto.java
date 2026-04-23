package com.devcode.svacca.security_project.model.auth.dto;

public record LoginResponseDto(
    String token,
    String type
) {
    
}
