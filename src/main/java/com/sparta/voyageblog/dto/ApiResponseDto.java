package com.sparta.voyageblog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto {
    private String message;
    private HttpStatus statusCode;
    private Object data;

    public ApiResponseDto(String message, HttpStatus status) {
        this.statusCode = status;
        this.message = message;
        this.data = null;
    }

}
