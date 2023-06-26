package com.sparta.voyageblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "^[A-Za-z0-9+]{4,10}")
    @NotBlank
    private String username;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+]{8,15}")
    private String password;
    @Email
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}