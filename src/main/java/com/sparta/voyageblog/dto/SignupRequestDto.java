package com.sparta.voyageblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "^[a-z0-9+]{4,10}$") //4~10글자 영어 소문자+숫자
    @NotBlank
    private String username;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9`~!@#$%^&*()-_=+]{8,15}$") //8~15글자 영어 대소문자+숫자+특수문자
    private String password;
    @Email //email 형식
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}