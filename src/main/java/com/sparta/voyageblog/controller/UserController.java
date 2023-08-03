package com.sparta.voyageblog.controller;


import com.sparta.voyageblog.dto.ApiResponseDto;
import com.sparta.voyageblog.dto.SignupRequestDto;
import com.sparta.voyageblog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<ApiResponseDto> signUp(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto("회원가입 완료", HttpStatus.CREATED));
    }
}
