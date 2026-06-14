package com.portfolio.taskmanager.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank
    private String name;
    @Email
    @Column(nullable = false)
    private String email;
    @NotBlank
    private String password;
}
