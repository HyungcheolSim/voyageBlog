package com.sparta.voyageblog.exception;

import com.sparta.voyageblog.dto.GeneralResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<GeneralResponseDto> handleException(IllegalArgumentException ex) {
        GeneralResponseDto responseDto = new GeneralResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(
                // HTTP body
                responseDto,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<GeneralResponseDto> nullPointerExceptionHandler(NullPointerException ex){
        GeneralResponseDto responseDto=new GeneralResponseDto(ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(
                responseDto,
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler({NotValidInputException.class})
    public ResponseEntity<GeneralResponseDto> notValidInputExceptionHandler(NotValidInputException ex){
        GeneralResponseDto responseDto=new GeneralResponseDto(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(
                responseDto,
                HttpStatus.BAD_REQUEST
        );
    }
}