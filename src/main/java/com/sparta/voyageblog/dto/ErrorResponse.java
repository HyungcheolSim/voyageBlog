package com.sparta.voyageblog.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse { //Exception Filter 용
    private static final ObjectMapper objectmapper=new ObjectMapper();

    private final String timeStamp;
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.timeStamp = LocalDateTime.now().toString();
        this.status = status.value();
        this.error = status.toString();
        this.message = message;
    }

    public String convertToJson() throws JsonProcessingException{
        return objectmapper.writeValueAsString(this);
    }
}
