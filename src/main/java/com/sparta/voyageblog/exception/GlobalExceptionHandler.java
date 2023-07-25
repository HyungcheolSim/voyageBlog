package com.sparta.voyageblog.exception;

import com.sparta.voyageblog.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@Slf4j(topic = "GlobalExceptionHandler")
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    @ExceptionHandler({IllegalArgumentException.class})
        public ResponseEntity<ApiResponseDto> handleException(IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST));
        }
           @ExceptionHandler({NotValidInputException.class})
        public ResponseEntity<ApiResponseDto> notValidInputExceptionHandler(NotValidInputException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST));
        }*/
    @ExceptionHandler({IllegalArgumentException.class, RejectedExecutionException.class, NotValidInputException.class})
    public ResponseEntity<ApiResponseDto> rejectedExecutionExceptionHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDto(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<ApiResponseDto> nullPointerExceptionHandler(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND));
    }

}