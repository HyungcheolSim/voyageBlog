package com.sparta.voyageblog.controller;


import com.sparta.voyageblog.dto.GeneralResponseDto;
import com.sparta.voyageblog.dto.SignupRequestDto;
import com.sparta.voyageblog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<GeneralResponseDto> signUp(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult){
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new GeneralResponseDto("회원가입 완료", HttpStatus.OK));
    }
}
