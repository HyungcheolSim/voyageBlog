package com.sparta.voyageblog.exception;

import com.sparta.voyageblog.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@Slf4j(topic = "GlobalExceptionHandler")
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, RejectedExecutionException.class, NotValidInputException.class})
    public ResponseEntity<ApiResponseDto> rejectedExecutionExceptionHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ApiResponseDto> nullPointerExceptionHandler(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND));
    }

    //@Valid 관련 exception 처리
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST));
    }


}