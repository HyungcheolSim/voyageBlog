package com.sparta.voyageblog.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeneralResponseDto {
    private String message;
    private HttpStatus statusCode;
}
