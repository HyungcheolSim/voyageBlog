package com.sparta.voyageblog.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginRequestDto {
    //TODO validation 적용
    private String username;
    private String password;
}